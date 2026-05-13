# Team Task Manager - Backend

This is the backend API for the Team Task Manager application.

## 🚀 Getting Started

1.  **Install Dependencies**:
    ```bash
    npm install
    ```
2.  **Environment Variables**:
    Create a `.env` file with:
    ```
    PORT=5000
    MONGODB_URI=your_mongodb_uri
    JWT_SECRET=your_jwt_secret
    NODE_ENV=production
    FRONTEND_URL=your_frontend_url
    ```
3.  **Run Development**:
    ```bash
    npm run dev
    ```
4.  **Start Production**:
    ```bash
    npm start
    ```

## 🛠 Tech Stack
- Node.js
- Express
- MongoDB (Mongoose)
- JWT Authentication

## 📂 Structure
- `controllers/`: Request handlers
- `models/`: Database schemas
- `routes/`: API endpoints
- `middleware/`: Auth and validation
- `config/`: Configuration (Database)
