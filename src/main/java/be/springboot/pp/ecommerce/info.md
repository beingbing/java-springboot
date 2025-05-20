## 🛒 E-Commerce Platform – Functional Requirements

### 🔐 Authentication
* User login/logout is assumed to be implemented and operational.

### 🔎 Product Discovery
* Users (guests or logged-in) can **search** for products using keywords.
* Search supports **filters** like:
    * Price range
    * Ratings
    * Other optional criteria (e.g., brand, category)

### 👤 User Roles and Permissions
#### 1. **Guest User**
* Can **search** for products.
* Cannot perform purchases or access user-specific features.
#### 2. **Logged-in Customer**
* Can **search** and **filter** products.
* Can **add products to cart**.
* Can **checkout** the cart by making a **payment**.
* Can **track orders** after payment (e.g., *in-transit*, *out-for-delivery*).
* Can receive **notifications** (via mobile, email, or WhatsApp).
* Can **rate** and **review** products after purchase.
#### 3. **Merchant / Seller**
* Can **list** new products for sale.
* Can **update or delete** their own product listings.
* Can **bulk add** multiple products at once (via upload or API).
#### 4. **Admin**
* Multiple types of admins exist, each with **different sets of permissions** (e.g., user moderation, product approval, analytics access).
* Specific admin functions are assumed to be role-dependent.

### 💳 Purchase and Payments
* Customers can make payments via **multiple payment modes** (e.g., credit card, UPI, net banking).
* Successful payments initiate the **order lifecycle** and enable **order tracking**.

### 🔔 Notifications
* The system supports **real-time notifications** for key events:
    * Order status updates
    * Payment confirmations
    * Promotions or alerts
* Channels include: **mobile push**, **email**, and **WhatsApp**.

### 💬 Feedback System
* Customers can **rate** products (typically on a 1–5 scale).
* Customers can leave **written reviews** or feedback.
  Based on the rewritten requirements for the e-commerce platform, here is a clear breakdown of:

## 🧑 Actors and Their Actions

### 1. **Guest User**
* 🔍 Search for products
* 🔍 Filter product listings (e.g., by price, rating)

### 2. **Logged-in Customer**
* 🔍 Search and filter products
* 🛒 Add products to cart
* 💳 Checkout and make payment
* 🚚 Track order status (e.g., in-transit, delivered)
* ✍️ Rate purchased products
* ✍️ Review/leave feedback on products
* 🔔 Receive notifications (mobile, email, WhatsApp)

### 3. **Merchant / Seller**
* ➕ List/add new products
* ✏️ Update their own product listings
* ❌ Delete their own product listings
* 📦 Bulk add products

### 4. **Admin (Various Types)**
* ⚙️ Perform role-specific functions (not exhaustively detailed, but may include moderation, analytics, approval workflows)

## 📦 Props (System Objects) and Actions on Them
### 1. **Product**
* 🔍 Searched (by all users)
* 🏷️ Filtered (by attributes like price, rating)
* 🛒 Added to cart (by customer)
* 🛍️ Purchased (by customer)
* ✍️ Rated and reviewed (by customer)
* ➕ Added (by seller)
* ✏️ Updated (by seller)
* ❌ Deleted (by seller)
* 📦 Bulk added (by seller)

### 2. **Cart**
* 🛒 Products added (by customer)
* 💳 Checked out (by customer)

### 3. **Order**
* 🚚 Tracked (by customer after checkout)
* 🔔 Trigger notifications (status updates)

### 4. **Payment**
* 💳 Made during checkout (by customer)
* 📬 Confirmed and logged

### 5. **Notification**
* 📱 Sent to customer (via mobile, email, WhatsApp)
* 🛎️ Triggered by events (e.g., order placed, status update)
