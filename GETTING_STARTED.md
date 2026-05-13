# Getting Started - Team Task Manager

## Quick Setup (5 minutes)

### Prerequisites
- Node.js (v14 or higher)
- MongoDB (local or Atlas)
- Git

### Step 1: Clone and Setup

```bash
# Navigate to project directory
cd assignmentfullstack

# Run setup script
# On Windows:
setup.bat

# On macOS/Linux:
bash setup.sh
```

### Step 2: Configure Environment

**Backend (.env)**
```
PORT=5000
MONGODB_URI=mongodb://localhost:27017/team-task-manager
JWT_SECRET=dev-secret-key-change-in-prod
NODE_ENV=development
FRONTEND_URL=http://localhost:3000
```

**Frontend (.env)**
```
REACT_APP_API_URL=http://localhost:5000/api
```

### Step 3: Start Services

**Terminal 1 - Backend:**
```bash
cd backend
npm run dev
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```

### Step 4: Access Application

- Frontend: http://localhost:3000
- Backend API: http://localhost:5000

## First Time Usage

1. **Register Account**
   - Go to sign-up page
   - Enter name, email, password
   - You'll be logged in automatically

2. **Create Project**
   - Click "Projects" in navbar
   - Click "New Project"
   - Add project details
   - Submit

3. **Add Team Members**
   - Open project
   - Add team members by their email
   - Set their role (admin/member)

4. **Create Tasks**
   - In project, click "New Task"
   - Fill task details
   - Assign to team member
   - Set priority and due date

5. **Track Progress**
   - Go to Dashboard
   - See stats and your tasks
   - Update task status as you work

## Database Setup

### Option 1: Local MongoDB (Recommended for Development)

```bash
# macOS (with Homebrew)
brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb-community

# Or use Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

### Option 2: MongoDB Atlas (Cloud)

1. Create account at mongodb.com
2. Create free cluster
3. Get connection string
4. Set `MONGODB_URI` in backend `.env`

## Project Structure

```
assignmentfullstack/
├── backend/
│   ├── models/
│   │   ├── User.js
│   │   ├── Project.js
│   │   └── Task.js
│   ├── controllers/
│   │   ├── userController.js
│   │   ├── projectController.js
│   │   └── taskController.js
│   ├── routes/
│   │   ├── userRoutes.js
│   │   ├── projectRoutes.js
│   │   └── taskRoutes.js
│   ├── middleware/
│   │   ├── auth.js
│   │   └── authorize.js
│   ├── config/
│   │   └── database.js
│   ├── utils/
│   │   └── auth.js
│   ├── server.js
│   └── package.json
│
├── frontend/
│   ├── src/
│   │   ├── pages/
│   │   │   ├── Auth.jsx
│   │   │   ├── Dashboard.jsx
│   │   │   ├── Projects.jsx
│   │   │   └── Tasks.jsx
│   │   ├── components/
│   │   │   ├── Navbar.jsx
│   │   │   └── ProtectedRoute.jsx
│   │   ├── services/
│   │   │   └── api.js
│   │   ├── context/
│   │   │   └── store.js
│   │   ├── App.js
│   │   └── index.js
│   ├── public/
│   │   └── index.html
│   └── package.json
│
├── README.md
├── DEPLOYMENT.md
├── GETTING_STARTED.md
├── docker-compose.yml
└── .gitignore
```

## Common Commands

```bash
# Backend
npm install          # Install dependencies
npm run dev         # Start development server
npm start           # Start production server

# Frontend
npm install         # Install dependencies
npm start           # Start dev server
npm run build       # Build for production
npm test            # Run tests
```

## Troubleshooting

### "Cannot connect to MongoDB"
- Check MongoDB is running
- Verify MONGODB_URI in .env
- Check connection string format

### "API calls failing"
- Verify backend is running
- Check REACT_APP_API_URL
- Check browser console for errors

### "Token invalid"
- Clear browser localStorage
- Log out and log back in
- Check JWT_SECRET is set

### "Port already in use"
- Change PORT in .env
- Or kill process: `lsof -i :5000` then `kill -9 <PID>`

## Testing Features

### Test Admin Functions
- Create multiple projects
- Add/remove team members
- Delete projects

### Test Member Functions
- Join projects
- Create and update tasks
- Add comments

### Test Task Management
- Create tasks with different priorities
- Set overdue tasks
- Update status
- Assign tasks

## Performance Tips

1. **Database Indexing**: MongoDB automatically creates indexes for common fields
2. **Frontend Optimization**: React.lazy() for code splitting
3. **API Caching**: Implement caching for frequently accessed data
4. **Pagination**: Implement pagination for large task lists

## Next Steps

1. **Deploy to Railway** - See DEPLOYMENT.md
2. **Add More Features**:
   - Task categories/tags
   - File attachments
   - Notifications
   - Team chat
3. **Improve UI/UX**:
   - Dark mode
   - Drag-and-drop tasks
   - Calendar view
4. **Add Tests**:
   - Unit tests
   - Integration tests
   - E2E tests

## Support

For issues:
1. Check this guide
2. Check DEPLOYMENT.md for deployment issues
3. Check browser console for errors
4. Create GitHub issue with error details

---

Happy coding! 🚀
