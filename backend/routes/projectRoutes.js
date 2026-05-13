const express = require('express');
const { body } = require('express-validator');
const {
  createProject,
  getAllProjects,
  getProjectById,
  updateProject,
  addMemberToProject,
  removeMemberFromProject,
  deleteProject,
} = require('../controllers/projectController');
const auth = require('../middleware/auth');

const router = express.Router();

// All routes require authentication
router.use(auth);

// Create project
router.post(
  '/',
  [
    body('name', 'Project name is required').trim().notEmpty(),
  ],
  createProject
);

// Get all projects
router.get('/', getAllProjects);

// Get project by ID
router.get('/:id', getProjectById);

// Update project
router.put(
  '/:id',
  [
    body('name', 'Project name is required').trim().notEmpty(),
  ],
  updateProject
);

// Add member to project
router.post(
  '/:id/members',
  [
    body('userId', 'User ID is required').notEmpty(),
  ],
  addMemberToProject
);

// Remove member from project
router.delete('/:id/members', removeMemberFromProject);

// Delete project
router.delete('/:id', deleteProject);

module.exports = router;
