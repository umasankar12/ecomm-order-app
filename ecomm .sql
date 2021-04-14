
CREATE SCHEMA IF NOT EXISTS sales;
CREATE SCHEMA IF NOT EXISTS customer;
CREATE SCHEMA IF NOT EXISTS inventory;


CREATE TABLE IF NOT EXISTS customer.Address (
    id          SERIAL PRIMARY KEY NOT NULL,
    line1       VARCHAR(200) NOT NULL,
    line2       VARCHAR(200),
    city        VARCHAR(100) NOT NULL ,
    state       VARCHAR(100) NOT NULL ,
    country     VARCHAR(100) NOT NULL ,
    zipcode     VARCHAR(10) NOT NULL,
    type        VARCHAR(20), -- HOME/OFFICE/etc
    nickname    VARCHAR(200)
);

create index if not exists idx_addr_zip on customer.Address(zipcode);
create index if not exists  idx_addr_country on customer.Address(country);
create index if not exists  idx_addr_state on customer.Address(state);
create index if not exists idx_addr_city on customer.Address(city);


CREATE TABLE IF NOT EXISTS customer.Payment2 (
    id            SERIAL PRIMARY KEY NOT NULL,
    type          VARCHAR(20), -- DEBIT/CREDIT/PAYPAL
    cardholder    VARCHAR(100) NOT NULL,
    card_num      VARCHAR(20) NOT NULL , -- 16 + 4
    provider      VARCHAR(20) NOT NULL , -- VISA, MASTERCARD, AMEX, etc
    expiry_date   DATE NOT NULL ,
    cvv           INT  NOT NULL,
    billing_address INT REFERENCES customer.Address(id)
);
create index if not exists idx_payment_cardholder ON customer.Payment(cardholder);
create unique index if not exists idx_payment_card_num ON customer.Payment(card_num);


CREATE TABLE IF NOT EXISTS customer.PaymentHistory(
    id              SERIAL PRIMARY KEY,    
    request_id		VARCHAR(100), -- ORDER_PAYMENT_ID
    payment_id		INT REFERENCES customer.Payment(id),
    action          VARCHAR(200), -- ADD_NEW, DISABLE, CHANGE_ADDRESS, VALIDATE_PAY, PAY_FOR_ORDER
    action_detail   VARCHAR(1000),
    status          BOOLEAN DEFAULT True,
    entry_time        TIMESTAMP,
    entry_data        JSON
);
create index if not exists idx_paymenthistory_request_id ON customer.PaymentHistory(request_id);



CREATE TABLE IF NOT EXISTS customer.Customer(
    id              SERIAL PRIMARY KEY NOT NULL,
    first_name      VARCHAR(100) NOT NULL ,
    last_name       VARCHAR(20)  NOT NULL ,
    gender          VARCHAR(1) DEFAULT 'M',
    phone           VARCHAR(20),
    email           VARCHAR(100),
    dob             DATE,
    primary_address INT REFERENCES customer.Address(id)
);
create index if not exists idx_cust_first_name on customer.Customer(first_name);
create index if not exists idx_cust_last_name on customer.Customer(last_name);
create index if not exists idx_cust_email on customer.Customer(email);
create index if not exists idx_cust_phone on customer.Customer(phone);


CREATE TABLE IF NOT EXISTS customer.GuestCustomer(
    id            SERIAL PRIMARY KEY NOT NULL,
    first_name     VARCHAR(100) NOT NULL ,
    last_name      VARCHAR(20)  NOT NULL ,
    gender        VARCHAR(1) DEFAULT 'M',
    phone_num     VARCHAR(20),
    email         VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS customer.CustomerAddress(
    customer_id     INT,
    address_id      INT
);


CREATE TABLE IF NOT EXISTS customer.PaymentType (
    id      SERIAL,
    name    VARCHAR(20) -- DEBIT/CREDIT/PAYPAL
);


CREATE TABLE IF NOT EXISTS customer.CustomerPayment(
    id                SERIAL PRIMARY KEY NOT NULL,
    customer_id       INT,
    guest_id          INT, -- either registered or guest
    payment_id        INT,
    start_date        DATE DEFAULT current_date,
    end_date          DATE,
    constraint fk_customer_id FOREIGN KEY(customer_id) references customer.Customer(id),
    constraint fk_guest_id FOREIGN KEY(guest_id) references customer.GuestCustomer(id),
    constraint fk_payment_id foreign key(payment_id) references customer.Payment(id)
);
-- Search all payment types registered for a customer
create index if not exists idx_cust_payment_cust_id On customer.CustomerPayment(customer_id);
create index if not exists idx_cust_payment_guest_id On customer.CustomerPayment(guest_id);



CREATE TABLE IF NOT EXISTS inventory.Item (
    id      SERIAL PRIMARY KEY,
    code    VARCHAR(20) NOT NULL ,
    name    VARCHAR(100) NOT NULL,
    description    VARCHAR(1000),
    unit    VARCHAR(20),
    unit_price  NUMERIC,
    avail_qty   NUMERIC,
    tags        VARCHAR(1000), -- comma separated list
    start_date  DATE,
    end_date    DATE
);
create unique index idx_item_code ON inventory.Item(code);


DROP TABLE IF EXISTS sales.Order CASCADE;
CREATE TABLE IF NOT EXISTS sales.Order (
  id            SERIAL PRIMARY KEY NOT NULL,
  customer_id       INT references customer.Customer(id),
  source 		VARCHAR(100) NOT NULL,
  source_id		VARCHAR(200),
  guest_id          INT references customer.GuestCustomer(id), -- either registered or guest
  shipping_address     INT REFERENCES customer.Address(id),
  total_amt     NUMERIC,
  create_date   TIMESTAMP,
  cancel_date   TIMESTAMP,
  return_date	TIMESTAMP,
  notes         VARCHAR(2000),
  status        VARCHAR(20) -- OPEN, CONFIRMED, PARTIALLY_SHIPPED, SHIPPED, PARTIALLY_DELIVERED
  							-- DELIVERED, RETURNED, CANCELLED
);
-- Search Columns
create index if not exists idx_order_cust_id ON sales.Order(customer_id);
create index if not exists idx_order_create_date ON sales.Order(create_date);
create index if not exists idx_order_source ON sales.Order(source);


DROP TABLE IF EXISTS sales.OrderItem CASCADE;
CREATE TABLE IF NOT EXISTS sales.OrderItem (
   id					SERIAL PRIMARY KEY,
   order_id             INT REFERENCES sales.Order(id),
   item_id              INT REFERENCES inventory.Item(id),
   item_qty             INT,
   shipping_ref         VARCHAR(100), -- shipping reference
   item_status          VARCHAR(20),
   notes                VARCHAR(1000)
);
create index if not exists idx_orderitem_order_id ON sales.OrderItem(order_id);


DROP TABLE IF EXISTS sales.OrderHistory CASCADE;
CREATE TABLE sales.OrderHistory(
    id              SERIAL PRIMARY KEY,
    order_item		INT REFERENCES sales.OrderItem(id),
    user_type       VARCHAR(10), -- GUEST/CUSTOMER/INTERNAL
    user_id         INT,
    employee_id		VARCHAR(100), --employeeid or system
    entry_time		TIMESTAMP DEFAULT Current_Timestamp,
    action          VARCHAR(200), -- ADD_ORDER, ADD_ITEM, REMOVE_ITEM, CANCEL_ITEM, CANCEL_ORDER etc
    action_detail   VARCHAR(1000),
    status          BOOLEAN DEFAULT True,
    notes			VARCHAR(2000),
    log_data        JSON
);
create index if not exists idx_orderhistory_order_item ON sales.OrderHistory(order_item);


DROP TABLE IF EXISTS sales.OrderPayment CASCADE;
CREATE TABLE sales.OrderPayment(
	id				SERIAL PRIMARY KEY,
    order_id        INT REFERENCES sales.Order(id),
    payment_id      INT REFERENCES customer.CustomerPayment(id),
    payment_type	VARCHAR(100) NOT NULL, -- PAY, REFUND
    amount		    NUMERIC,
    status			BOOLEAN, -- true/false
    payment_code	VARCHAR(1000), --onetime_confirm_code
    response_code   VARCHAR(1000),
    reason_code		VARCHAR(100), --cust_cancel
    request_time    TIMESTAMP,
    response_time	TIMESTAMP
);
create index if not exists idx_orderpayment_order_id ON sales.OrderPayment(order_id);



CREATE TABLE IF NOT EXISTS sales.OrderDetail (
   order_id             INT REFERENCES sales.Order(id),
   item_id              INT REFERENCES inventory.Item(id),
   item_qty             INT,
   shipping_address     INT,
   shipping_ref         VARCHAR(100), -- shipping reference
   item_status          VARCHAR(20),
   notes                VARCHAR(1000),
   shipping_date        TIMESTAMP,
   delivery_date        TIMESTAMP,
   cancel_date          TIMESTAMP
);


CREATE TABLE IF NOT EXISTS sales.OrderLog(
    id              SERIAL,
    order_id		INT NOT NULL REFERENCES sales.Order(id),
    item_id			INT NOT NULL REFERENCES inventory.Item(id),
    log_time        TIMESTAMP,
    user_type       VARCHAR(10), -- GUEST/CUSTOMER/INTERNAL
    user_id         INT,
    employee_id		VARCHAR(100),
    action          VARCHAR(200), -- ADD_ORDER, ADD_ITEM, REMOVE_ITEM, CANCEL_ITEM, CANCEL_ORDER etc
    action_detail   VARCHAR(1000),
    status          BOOLEAN DEFAULT True,
    log_data        JSON
);


CREATE TABLE IF NOT EXISTS customer.PaymentLog(
    id              SERIAL PRIMARY KEY,    
    request_id		VARCHR(100),
    order_payment_id		INT REFERENCES sales.OrderPayment(id),  - OrderPaymentId
    action          VARCHAR(200), -- VALIDATE_PAYMENT, PROCESS_PAYMENT
    action_detail   VARCHAR(1000),
    status          BOOLEAN DEFAULT True,
    log_time        TIMESTAMP,
    log_data        JSON
);
create index if not exists idx_paymentlog_request_id ON customer.PaymentLog(request_id);





