# Testing Checklist - Team Task Manager

## Setup Checklist

- [ ] Node.js installed
- [ ] MongoDB installed/running
- [ ] Backend dependencies installed: `npm install` in backend/
- [ ] Frontend dependencies installed: `npm install` in frontend/
- [ ] .env files created and configured
- [ ] Backend running on http://localhost:5000
- [ ] Frontend running on http://localhost:3000

## Authentication Testing

### Registration
- [ ] User can register with valid email
- [ ] User gets JWT token after registration
- [ ] Cannot register with duplicate email
- [ ] Cannot register with invalid email
- [ ] Cannot register with short password (<6 chars)

### Login
- [ ] User can login with correct credentials
- [ ] User gets JWT token on login
- [ ] Cannot login with wrong password
- [ ] Cannot login with non-existent email
- [ ] Token persists in localStorage

### User Profile
- [ ] Can view current user profile
- [ ] Can update user profile (name, avatar)
- [ ] Can see user role (admin/member)

## Project Management Testing

### Create Projects
- [ ] User can create project
- [ ] Project has correct owner (current user)
- [ ] Creator is added as admin member
- [ ] Can set project name and description
- [ ] Can set optional due date
- [ ] Project status defaults to "active"

### View Projects
- [ ] User sees all projects they own
- [ ] User sees all projects they're member of
- [ ] Cannot see projects they're not part of
- [ ] Project list shows member count
- [ ] Project list shows status

### Update Projects
- [ ] Project owner can update project
- [ ] Cannot update if not owner
- [ ] Can change name, description, status
- [ ] Can set due dates
- [ ] Changes persist after refresh

### Delete Projects
- [ ] Project owner can delete project
- [ ] All tasks in project are deleted
- [ ] Cannot delete if not owner
- [ ] Project removed from list after delete

### Team Management
- [ ] Can add existing user to project
- [ ] Can set member role (admin/member)
- [ ] Cannot add user twice
- [ ] Can remove member from project
- [ ] Cannot remove if not owner

## Task Management Testing

### Create Tasks
- [ ] User can create task in their project
- [ ] Cannot create in project not member of
- [ ] Can set title and description
- [ ] Can set priority (low/medium/high/urgent)
- [ ] Can set due date
- [ ] Can assign to team member
- [ ] Can leave unassigned
- [ ] Task status defaults to "todo"

### View Tasks
- [ ] See all tasks in project
- [ ] Filter by status
- [ ] Filter by priority
- [ ] Filter by assignee
- [ ] Task shows all metadata

### Update Tasks
- [ ] Task creator can update task
- [ ] Task assignee can update task
- [ ] Project owner can update task
- [ ] Can change status (4 values)
- [ ] Can change priority
- [ ] Can reassign task
- [ ] Can update due date
- [ ] Changes save correctly

### Task Comments
- [ ] Can add comment to task
- [ ] Comment shows username and timestamp
- [ ] Multiple comments displayed in order
- [ ] Comments persist

### Task Status Workflow
- [ ] Create task (default: todo)
- [ ] Move to in-progress
- [ ] Move to in-review
- [ ] Move to completed
- [ ] Can change back to previous status

### Overdue Tasks
- [ ] Task with due date < today shows isOverdue = true
- [ ] Completed tasks never marked overdue
- [ ] Dashboard shows overdue count
- [ ] Overdue indicator appears on task

### Delete Tasks
- [ ] Task creator can delete
- [ ] Project owner can delete
- [ ] Task removed from list
- [ ] Task removed from database

## Dashboard Testing

### Statistics
- [ ] Total tasks count correct
- [ ] Completed tasks count correct
- [ ] Overdue tasks count correct
- [ ] Assigned to me count correct

### My Tasks Section
- [ ] Shows up to 5 tasks assigned to me
- [ ] Shows only incomplete tasks
- [ ] Sorted by due date
- [ ] Can click to view task

### Recent Projects
- [ ] Shows recently accessed projects
- [ ] Shows member count
- [ ] Shows project status
- [ ] Can navigate to project

## Navigation Testing

### Navbar
- [ ] Navbar shows on all protected pages
- [ ] Shows user name
- [ ] Shows user role
- [ ] Can navigate to Dashboard
- [ ] Can navigate to Projects
- [ ] Can logout

### Routing
- [ ] Unauthenticated users redirected to login
- [ ] Can access dashboard when logged in
- [ ] Can access projects when logged in
- [ ] Can access task details when logged in
- [ ] 404s redirect to dashboard

## Authorization Testing

### Role-Based Access
- [ ] Only owner can edit project
- [ ] Only owner can delete project
- [ ] Only owner can manage members
- [ ] Members can view projects
- [ ] Members can create/edit tasks
- [ ] Non-members cannot access project

### Data Privacy
- [ ] User can only see their own projects
- [ ] User cannot see private projects
- [ ] Cannot access other user's data directly via API

## UI/UX Testing

### Forms
- [ ] All required fields have validation
- [ ] Error messages display correctly
- [ ] Form disables on submit
- [ ] Success messages show
- [ ] Forms clear on success

### Responsiveness
- [ ] Layout works on desktop (1920px)
- [ ] Layout works on tablet (768px)
- [ ] Layout works on mobile (375px)
- [ ] No horizontal scroll
- [ ] Buttons are touch-friendly

### Loading States
- [ ] Loading indicator shows while fetching
- [ ] Buttons show loading state
- [ ] Forms disable during submission

## API Testing

### Request/Response
- [ ] API returns correct status codes
- [ ] Error messages are descriptive
- [ ] Responses have proper structure
- [ ] No sensitive data in responses

### Token Management
- [ ] Token sent in Authorization header
- [ ] Invalid tokens rejected
- [ ] Expired tokens trigger re-login
- [ ] Token refreshed after login

### Error Handling
- [ ] 400 errors show validation messages
- [ ] 401 errors trigger logout
- [ ] 403 errors show permission denied
- [ ] 404 errors show not found
- [ ] 500 errors show generic message

## Performance Testing

- [ ] Page loads within 3 seconds
- [ ] Dashboard stats load quickly
- [ ] Project list loads smoothly
- [ ] No console errors
- [ ] No memory leaks (check DevTools)

## Browser Compatibility

- [ ] Chrome latest
- [ ] Firefox latest
- [ ] Safari latest
- [ ] Edge latest

## Deployment Testing

- [ ] Backend builds successfully
- [ ] Frontend builds successfully
- [ ] Docker containers run locally
- [ ] Environment variables work
- [ ] Database connection works
- [ ] API accessible from frontend

## Bug Report Template

When testing, if you find issues:

```
**Title**: [Brief description]

**Steps to Reproduce**:
1. 
2. 
3. 

**Expected Result**:

**Actual Result**:

**Browser/Device**:

**Severity**: [Critical/High/Medium/Low]
```

---

## Successful Testing = Ready for Production ✅
