## In-Memory MySQL Server: Functional Requirements
### 1. Architecture
* The entire system operates **in RAM** (memory-only, no disk persistence).
* The server will manage **exactly one database**.

### 2. Data Model
* The database supports **multiple tables**.
* Each table consists of **rows and columns**.
* **Column data types** are restricted to **strings only**.

### 3. Table Operations
* Tables can be:
    * **Created** and **deleted**.
    * **Altered** by adding or removing columns.
    * **Modified** by inserting or deleting rows.

### 4. Constraints
* **Primary Key (PK)** and **Foreign Key (FK)** constraints are supported at the table level.

### 5. Query Capabilities
* Supports **row selection** and **row updates** based on conditions.
* The **WHERE clause** can include:
    * **Direct comparisons** between columns and constants (e.g., `col = 'value'`)
    * Logical connectors: **AND**, **OR**
    * **Parentheses** to group conditions and control evaluation order
