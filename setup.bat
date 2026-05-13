@echo off
echo 🚀 Team Task Manager - Setup Script
echo ====================================
echo.

echo Installing backend dependencies...
cd backend
call npm install
cd ..

echo.
echo Installing frontend dependencies...
cd frontend
call npm install
cd ..

echo.
echo ✓ Setup complete!
echo.
echo Next steps:
echo 1. Update .env files with your configuration
echo 2. Make sure MongoDB is running
echo 3. Run backend: cd backend ^&^& npm run dev
echo 4. Run frontend: cd frontend ^&^& npm start
echo.
echo Backend will run on http://localhost:5000
echo Frontend will run on http://localhost:3000
