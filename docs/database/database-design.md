# Database Design

## Menu Service Database

### categories

| Column | Type |
|---------|------|
| id | BIGINT |
| name | VARCHAR |
| description | VARCHAR |
| active | BOOLEAN |

---

### menu_items

| Column | Type |
|---------|------|
| id | BIGINT |
| name | VARCHAR |
| description | VARCHAR |
| price | DECIMAL |
| available | BOOLEAN |
| preparation_time | INT |
| image_url | VARCHAR |
| category_id | BIGINT |

---

## Order Service Database

### orders

| Column | Type |
|---------|------|
| id | BIGINT |
| customer_name | VARCHAR |
| customer_phone | VARCHAR |
| customer_address | VARCHAR |
| total_amount | DECIMAL |
| status | ENUM |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

---

### order_items

| Column | Type |
|---------|------|
| id | BIGINT |
| menu_item_id | BIGINT |
| menu_item_name | VARCHAR |
| quantity | INT |
| price | DECIMAL |
| subtotal | DECIMAL |
| order_id | BIGINT |