INSERT INTO category (description, name)
VALUES ('Devices used for personal health monitoring and management.', 'Wearable Devices'),
       ('Medical equipment designed for use in hospitals.', 'Hospital Equipment'),
       ('Devices and instruments used in diagnostic procedures.', 'Diagnostic Tools'),
       ('Medical devices that assist in patient rehabilitation.', 'Rehabilitation Devices'),
       ('IoT-enabled devices for remote patient monitoring.', 'Remote Monitoring Devices');


INSERT INTO product (description, name, available_quantity, price, category_id)
VALUES ('Smartwatch with heart rate monitoring and ECG capabilities.', 'Smart Health Watch', 500, 199.99,
        (SELECT id FROM category WHERE name = 'Wearable Devices')),
       ('Portable insulin pump with Bluetooth connectivity.', 'Insulin Pump', 300, 299.99,
        (SELECT id FROM category WHERE name = 'Wearable Devices')),
       ('IoT-enabled ventilator with remote monitoring features.', 'IoT Ventilator', 100, 4999.99,
        (SELECT id FROM category WHERE name = 'Hospital Equipment')),
       ('MRI machine with enhanced imaging capabilities.', 'Advanced MRI Scanner', 20, 250000.00,
        (SELECT id FROM category WHERE name = 'Diagnostic Tools')),
       ('Rehabilitation robot for physical therapy.', 'Therapy Robot', 50, 15000.00,
        (SELECT id FROM category WHERE name = 'Rehabilitation Devices')),
       ('Remote monitoring device for continuous glucose monitoring.', 'CGM Device', 400, 249.99,
        (SELECT id FROM category WHERE name = 'Remote Monitoring Devices')),
       ('IoT-connected blood pressure monitor.', 'IoT Blood Pressure Monitor', 600, 89.99,
        (SELECT id FROM category WHERE name = 'Remote Monitoring Devices'));
