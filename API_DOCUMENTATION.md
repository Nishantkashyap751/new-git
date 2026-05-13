# API Documentation - Team Task Manager

## Base URL
```
http://localhost:5000/api
```

## Authentication

All protected endpoints require the JWT token to be sent in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

Tokens are returned on login/register and expire after 7 days.

---

## User Endpoints

### 1. Register User
**POST** `/users/register`

Create a new user account.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response (201):**
```json
{
  "message": "User registered successfully",
  "token": "jwt_token_here",
  "user": {
    "id": "user_id",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "member"
  }
}
```

**Validation:**
- Name: Required, non-empty
- Email: Valid email format, unique
- Password: Minimum 6 characters

---

### 2. Login User
**POST** `/users/login`

Authenticate and get JWT token.

**Request Body:**
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

**Response (200):**
```json
{
  "message": "Login successful",
  "token": "jwt_token_here",
  "user": {
    "id": "user_id",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "member"
  }
}
```

---

### 3. Get Current User
**GET** `/users/me`

Get authenticated user's profile.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "user": {
    "_id": "user_id",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "member",
    "avatar": "avatar_url",
    "isActive": true,
    "createdAt": "2024-01-15T10:00:00Z"
  }
}
```

---

### 4. Get All Users
**GET** `/users/all`

Get list of all active users.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "users": [
    {
      "_id": "user_id",
      "name": "John Doe",
      "email": "john@example.com",
      "role": "member",
      "avatar": "avatar_url"
    }
  ]
}
```

---

### 5. Update User
**PUT** `/users/update`

Update user profile.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "name": "Jane Doe",
  "avatar": "new_avatar_url"
}
```

**Response (200):**
```json
{
  "message": "User updated successfully",
  "user": {
    "_id": "user_id",
    "name": "Jane Doe",
    "email": "jane@example.com",
    "role": "member",
    "avatar": "new_avatar_url"
  }
}
```

---

## Project Endpoints

### 1. Create Project
**POST** `/projects`

Create a new project.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "name": "Website Redesign",
  "description": "Redesign company website",
  "dueDate": "2024-12-31"
}
```

**Response (201):**
```json
{
  "message": "Project created successfully",
  "project": {
    "_id": "project_id",
    "name": "Website Redesign",
    "description": "Redesign company website",
    "owner": {
      "_id": "user_id",
      "name": "John Doe",
      "email": "john@example.com"
    },
    "members": [
      {
        "user": {
          "_id": "user_id",
          "name": "John Doe"
        },
        "role": "admin"
      }
    ],
    "status": "active",
    "dueDate": "2024-12-31",
    "createdAt": "2024-01-15T10:00:00Z"
  }
}
```

**Validation:**
- Name: Required, non-empty

---

### 2. Get All Projects
**GET** `/projects`

Get all projects where user is owner or member.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "projects": [
    {
      "_id": "project_id",
      "name": "Website Redesign",
      "description": "Redesign company website",
      "owner": { /* owner data */ },
      "members": [],
      "status": "active"
    }
  ]
}
```

---

### 3. Get Project Details
**GET** `/projects/:id`

Get specific project details.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "project": {
    "_id": "project_id",
    "name": "Website Redesign",
    "description": "Redesign company website",
    "owner": { /* owner data */ },
    "members": [
      {
        "user": { "_id": "user_id", "name": "Jane Doe" },
        "role": "member"
      }
    ],
    "status": "active",
    "startDate": "2024-01-15T10:00:00Z",
    "dueDate": "2024-12-31",
    "createdAt": "2024-01-15T10:00:00Z",
    "updatedAt": "2024-01-15T10:00:00Z"
  }
}
```

---

### 4. Update Project
**PUT** `/projects/:id`

Update project details. Only project owner can update.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "name": "Website Redesign 2024",
  "description": "Updated description",
  "status": "active",
  "dueDate": "2024-12-31"
}
```

**Response (200):**
```json
{
  "message": "Project updated successfully",
  "project": { /* updated project */ }
}
```

---

### 5. Add Member to Project
**POST** `/projects/:id/members`

Add a member to project. Only project owner can add members.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "userId": "user_id",
  "role": "member"
}
```

**Response (200):**
```json
{
  "message": "Member added to project successfully",
  "project": { /* updated project */ }
}
```

---

### 6. Remove Member from Project
**DELETE** `/projects/:id/members`

Remove a member from project. Only project owner can remove members.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "userId": "user_id"
}
```

**Response (200):**
```json
{
  "message": "Member removed from project successfully",
  "project": { /* updated project */ }
}
```

---

### 7. Delete Project
**DELETE** `/projects/:id`

Delete a project and all its tasks. Only project owner can delete.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "message": "Project deleted successfully"
}
```

---

## Task Endpoints

### 1. Create Task
**POST** `/tasks`

Create a new task in a project.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "title": "Design homepage",
  "description": "Create mockups for homepage",
  "projectId": "project_id",
  "assigneeId": "user_id",
  "priority": "high",
  "dueDate": "2024-02-15"
}
```

**Response (201):**
```json
{
  "message": "Task created successfully",
  "task": {
    "_id": "task_id",
    "title": "Design homepage",
    "description": "Create mockups for homepage",
    "project": "project_id",
    "assignee": {
      "_id": "user_id",
      "name": "Jane Doe",
      "email": "jane@example.com"
    },
    "createdBy": {
      "_id": "user_id",
      "name": "John Doe"
    },
    "status": "todo",
    "priority": "high",
    "dueDate": "2024-02-15",
    "isOverdue": false,
    "comments": [],
    "attachments": [],
    "createdAt": "2024-01-15T10:00:00Z"
  }
}
```

**Validation:**
- Title: Required, non-empty
- ProjectId: Required, must exist
- User must be project member

---

### 2. Get Tasks by Project
**GET** `/tasks/project/:projectId`

Get all tasks in a project with optional filtering.

**Headers Required:** Authorization token

**Query Parameters:**
- `status`: Filter by status (todo, in-progress, in-review, completed)
- `priority`: Filter by priority (low, medium, high, urgent)
- `assignee`: Filter by assignee ID

**Examples:**
```
GET /tasks/project/project_id?status=in-progress
GET /tasks/project/project_id?priority=high&assignee=user_id
```

**Response (200):**
```json
{
  "tasks": [
    {
      "_id": "task_id",
      "title": "Design homepage",
      "status": "in-progress",
      "priority": "high",
      "assignee": { /* assignee */ },
      "createdBy": { /* creator */ },
      "dueDate": "2024-02-15",
      "isOverdue": false
    }
  ]
}
```

---

### 3. Get Dashboard Statistics
**GET** `/tasks/dashboard/stats`

Get dashboard statistics for authenticated user.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "stats": {
    "totalTasks": 15,
    "completedTasks": 8,
    "overdueTasks": 2,
    "assignedToMe": 5
  },
  "myTasks": [
    {
      "_id": "task_id",
      "title": "Design homepage",
      "status": "in-progress",
      "priority": "high",
      "project": {
        "_id": "project_id",
        "name": "Website Redesign"
      }
    }
  ],
  "totalProjects": 3
}
```

---

### 4. Get Task Details
**GET** `/tasks/:id`

Get detailed information about a specific task.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "task": {
    "_id": "task_id",
    "title": "Design homepage",
    "description": "Create mockups for homepage",
    "project": "project_id",
    "assignee": { /* assignee */ },
    "createdBy": { /* creator */ },
    "status": "in-progress",
    "priority": "high",
    "dueDate": "2024-02-15",
    "isOverdue": false,
    "comments": [
      {
        "user": { "_id": "user_id", "name": "Jane Doe" },
        "text": "Great work so far!",
        "createdAt": "2024-01-15T10:00:00Z"
      }
    ],
    "attachments": [],
    "createdAt": "2024-01-15T10:00:00Z",
    "updatedAt": "2024-01-15T10:00:00Z"
  }
}
```

---

### 5. Update Task
**PUT** `/tasks/:id`

Update task details. Task creator or assignee can update.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "title": "Design homepage",
  "description": "Create mockups for homepage",
  "status": "in-review",
  "priority": "medium",
  "assigneeId": "user_id",
  "dueDate": "2024-02-20"
}
```

**Response (200):**
```json
{
  "message": "Task updated successfully",
  "task": { /* updated task */ }
}
```

**Status Values:** `todo`, `in-progress`, `in-review`, `completed`

**Priority Values:** `low`, `medium`, `high`, `urgent`

---

### 6. Add Comment to Task
**POST** `/tasks/:id/comments`

Add a comment to a task.

**Headers Required:** Authorization token

**Request Body:**
```json
{
  "text": "This looks good, let's review it"
}
```

**Response (200):**
```json
{
  "message": "Comment added successfully",
  "task": {
    /* task with new comment */
  }
}
```

**Validation:**
- Text: Required, non-empty

---

### 7. Delete Task
**DELETE** `/tasks/:id`

Delete a task. Only task creator or project owner can delete.

**Headers Required:** Authorization token

**Response (200):**
```json
{
  "message": "Task deleted successfully"
}
```

---

## Error Responses

### 400 - Bad Request
```json
{
  "errors": [
    {
      "msg": "Email is required",
      "param": "email"
    }
  ]
}
```

### 401 - Unauthorized
```json
{
  "message": "No authentication token, access denied"
}
```

### 403 - Forbidden
```json
{
  "message": "Not authorized to perform this action"
}
```

### 404 - Not Found
```json
{
  "message": "Project not found"
}
```

### 500 - Server Error
```json
{
  "message": "Internal server error"
}
```

---

## Testing the API

### Using cURL

```bash
# Register
curl -X POST http://localhost:5000/api/users/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","password":"pass123"}'

# Login
curl -X POST http://localhost:5000/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"pass123"}'

# Get user (requires token)
curl -X GET http://localhost:5000/api/users/me \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

### Using Postman

1. Import collection from API endpoints
2. Set base URL: `http://localhost:5000/api`
3. Create environment variables:
   - `base_url`: http://localhost:5000/api
   - `token`: (auto-populate from login response)
4. Test each endpoint

---

## Rate Limiting

Currently no rate limiting implemented. For production:
- Implement rate limiting (e.g., express-rate-limit)
- Limit: 100 requests per 15 minutes per IP

## Pagination

For large datasets, implement pagination:
- Add `page` and `limit` query parameters
- Return `total`, `page`, `pages` in response

---

## CORS Policy

Frontend URL is configurable via `FRONTEND_URL` environment variable. Only requests from this URL are accepted.
