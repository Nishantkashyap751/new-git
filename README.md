# Team Task Manager - Full Stack Application

A modern web application for managing team projects, tasks, and collaboration with role-based access control.

## 🚀 Features

- **Authentication**: Secure user registration and login with JWT tokens
- **Project Management**: Create, update, and manage projects with team members
- **Task Management**: Create, assign, and track tasks with status updates
- **Role-Based Access Control**: Admin and Member roles with specific permissions
- **Dashboard**: Real-time dashboard showing task statistics and overdue tasks
- **Team Collaboration**: Assign tasks, add comments, and track progress
- **Responsive Design**: Mobile-friendly UI for all devices

## 🛠️ Tech Stack

### Backend
- Node.js with Express.js
- MongoDB for data persistence
- JWT for authentication
- bcryptjs for password hashing
- express-validator for input validation

### Frontend
- React 18
- React Router v6 for navigation
- Axios for API calls
- Zustand for state management
- CSS3 for styling

## 📋 Project Structure

```
assignmentfullstack/
├── backend/
│   ├── models/           # MongoDB schemas
│   ├── controllers/      # API logic
│   ├── routes/          # API endpoints
│   ├── middleware/      # Auth & RBAC
│   ├── config/          # Database config
│   ├── utils/           # Helper functions
│   ├── server.js        # Express server
│   └── package.json
│
├── frontend/
│   ├── src/
│   │   ├── pages/       # React pages
│   │   ├── components/  # Reusable components
│   │   ├── services/    # API services
│   │   ├── context/     # State management
│   │   ├── App.js
│   │   └── index.js
│   ├── public/          # Static files
│   └── package.json
│
├── docker-compose.yml   # Docker configuration
├── README.md
└── .gitignore
```

## 🔑 Key Database Models

### User
- Email, Name, Password (hashed)
- Role (admin/member)
- Timestamps

### Project
- Name, Description
- Owner reference
- Members array with roles
- Status (active/archived/completed)
- Timestamps

### Task
- Title, Description
- Project reference
- Assignee and Creator references
- Status (todo/in-progress/in-review/completed)
- Priority (low/medium/high/urgent)
- Due date and overdue tracking
- Comments array

## 🔐 Role-Based Access Control

### Admin
- Create/edit projects
- Add/remove team members
- Delete projects
- Full task management

### Member
- View assigned projects
- Create/update own tasks
- Add comments
- View project dashboard

## 📦 Installation & Setup

### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Install dependencies:
```bash
npm install
```

3. Create `.env` file:
```
PORT=5000
MONGODB_URI=mongodb://localhost:27017/team-task-manager
JWT_SECRET=your_jwt_secret_key_here
NODE_ENV=development
FRONTEND_URL=http://localhost:3000
```

4. Start the server:
```bash
npm run dev
```

### Frontend Setup

1. Navigate to frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Create `.env` file:
```
REACT_APP_API_URL=http://localhost:5000/api
```

4. Start the development server:
```bash
npm start
```

## 🚀 Deployment on Railway

### Prerequisites
- Railway account (free at railway.app)
- GitHub repository

### Deployment Steps

1. **Push code to GitHub**:
```bash
git init
git add .
git commit -m "Initial commit"
git push origin main
```

2. **Deploy Backend**:
   - Go to Railway dashboard
   - Click "New Project" → "Deploy from GitHub"
   - Select your repository
   - Add environment variables:
     - `PORT`: 5000
     - `MONGODB_URI`: Your MongoDB connection string
     - `JWT_SECRET`: Your secret key
     - `FRONTEND_URL`: Your deployed frontend URL

3. **Deploy Frontend**:
   - Create new service on Railway
   - Set environment variables:
     - `REACT_APP_API_URL`: Your deployed backend URL
   - Build command: `npm run build`
   - Start command: `npx serve -s build`

## 📝 API Endpoints

### Authentication
- `POST /api/users/register` - Register new user
- `POST /api/users/login` - Login user
- `GET /api/users/me` - Get current user (protected)

### Projects
- `GET /api/projects` - Get all user projects
- `POST /api/projects` - Create project
- `GET /api/projects/:id` - Get project details
- `PUT /api/projects/:id` - Update project
- `DELETE /api/projects/:id` - Delete project
- `POST /api/projects/:id/members` - Add member
- `DELETE /api/projects/:id/members` - Remove member

### Tasks
- `GET /api/tasks/dashboard/stats` - Get dashboard statistics
- `POST /api/tasks` - Create task
- `GET /api/tasks/project/:projectId` - Get tasks by project
- `GET /api/tasks/:id` - Get task details
- `PUT /api/tasks/:id` - Update task
- `POST /api/tasks/:id/comments` - Add comment
- `DELETE /api/tasks/:id` - Delete task

## 💡 Usage Guide

### Creating a Project
1. Log in to the application
2. Navigate to "Projects"
3. Click "New Project"
4. Fill in project details and submit
5. Add team members to the project

### Creating and Assigning Tasks
1. Open a project
2. Click "New Task"
3. Fill in task details (title, priority, due date)
4. Assign to a team member
5. Click "Create Task"

### Tracking Progress
1. Visit Dashboard to see all statistics
2. View overdue tasks and completion status
3. Update task status as you work
4. Add comments to collaborate with team members

## 🧪 Testing

Create test accounts:
- **Admin Account**: Use first registered account (becomes member by default)
- **Member Account**: Register additional accounts

## 📸 Features Demonstration

### Dashboard
- Total tasks count
- Completed tasks
- Overdue tasks alert
- Tasks assigned to you
- Recent projects

### Projects Page
- All your projects
- Create new projects
- View project members
- Quick access to project tasks

### Task Management
- Create detailed tasks
- Set priority and due dates
- Assign to team members
- Track status changes
- Comment and collaborate

### Team Collaboration
- Add/remove members from projects
- Assign tasks to specific people
- Comment on tasks
- Real-time status updates

## 🐛 Troubleshooting

### Backend Connection Issues
- Ensure MongoDB is running
- Check `MONGODB_URI` in `.env`
- Verify API URL is correct

### Frontend Not Loading Data
- Check browser console for errors
- Verify backend is running on correct port
- Check `REACT_APP_API_URL` in frontend `.env`

### Authentication Problems
- Clear browser localStorage
- Check JWT token expiration
- Verify JWT_SECRET matches

## 📄 License

MIT License - feel free to use for personal and commercial projects.

## 👥 Contributing

Contributions are welcome! Please submit pull requests with clear descriptions of changes.

## 📞 Support

For issues or questions, please create an issue in the GitHub repository.

---

**Built with ❤️ for team collaboration**
