const express = require('express');
const { body } = require('express-validator');
const {
  createTask,
  getTasksByProject,
  getTaskById,
  updateTask,
  addComment,
  deleteTask,
  getDashboardStats,
} = require('../controllers/taskController');
const auth = require('../middleware/auth');

const router = express.Router();

// All routes require authentication
router.use(auth);

// Create task
router.post(
  '/',
  [
    body('title', 'Task title is required').trim().notEmpty(),
    body('projectId', 'Project ID is required').notEmpty(),
  ],
  createTask
);

// Get dashboard stats
router.get('/dashboard/stats', getDashboardStats);

// Get tasks by project
router.get('/project/:projectId', getTasksByProject);

// Get task by ID
router.get('/:id', getTaskById);

// Update task
router.put('/:id', updateTask);

// Add comment to task
router.post('/:id/comments', [body('text', 'Comment text is required').notEmpty()], addComment);

// Delete task
router.delete('/:id', deleteTask);

module.exports = router;
