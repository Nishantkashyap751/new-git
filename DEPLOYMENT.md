# Deployment Guide for Railway

## Quick Start on Railway.app

### Step 1: Prepare Your Repository

1. Initialize git (if not already done):
```bash
git init
git add .
git commit -m "Team Task Manager - Full Stack"
git branch -M main
git remote add origin <your-github-repo-url>
git push -u origin main
```

### Step 2: Set Up MongoDB

1. Go to railway.app and create a new project
2. Click "New" → "Database" → "MongoDB"
3. The MongoDB connection string will be automatically provided

### Step 3: Deploy Backend

1. In your Railway project, click "New" → "GitHub Repo"
2. Select your repository
3. Choose the `backend` as root directory
4. Set environment variables:
   ```
   PORT=5000
   MONGODB_URI=<paste-your-mongodb-connection-string>
   JWT_SECRET=your-super-secret-key-change-this
   NODE_ENV=production
   FRONTEND_URL=https://<your-frontend-url>
   ```
5. Wait for deployment to complete
6. Note the backend service URL

### Step 4: Deploy Frontend

1. Create another service, click "New" → "GitHub Repo"
2. Select your repository
3. Choose the `frontend` as root directory
4. Set environment variables:
   ```
   REACT_APP_API_URL=https://<your-backend-url>/api
   ```
5. Set build and start commands in Settings:
   - Build: `npm run build`
   - Start: `npx serve -s build -l 3000`
6. Deploy

### Step 5: Verify Deployment

1. Visit your frontend URL
2. Create an account
3. Create a project and add a task
4. Check all features are working

## Production Checklist

- [ ] MongoDB is running on Railway
- [ ] Backend is deployed and accessible
- [ ] Frontend is deployed and accessible
- [ ] Authentication works
- [ ] Can create projects
- [ ] Can create tasks
- [ ] Can assign tasks
- [ ] Dashboard loads statistics
- [ ] Comments work
- [ ] Task status updates work

## Troubleshooting

### Backend Not Starting
- Check logs in Railway dashboard
- Verify MONGODB_URI is correct
- Check JWT_SECRET is set

### Frontend Not Loading Data
- Check browser console for errors
- Verify REACT_APP_API_URL is correct
- Check backend service is running

### CORS Errors
- Ensure FRONTEND_URL is set correctly in backend
- Backend CORS configuration allows requests from frontend domain

## Scaling Tips

- Use Railway's auto-scaling features for high traffic
- Monitor database connection pool
- Set up error logging and monitoring
- Use Railway's analytics dashboard

## Security Considerations

- Change JWT_SECRET to a strong random string
- Never commit .env files with real values
- Use Railway's secrets manager for sensitive data
- Enable HTTPS (Railway handles this automatically)
- Implement rate limiting for API endpoints

---

For more help, visit Railway documentation: https://railway.app/docs
