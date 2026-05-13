const express = require('express');
const { body } = require('express-validator');
const {
  register,
  login,
  getCurrentUser,
  getAllUsers,
  updateUser,
} = require('../controllers/userController');
const auth = require('../middleware/auth');

const router = express.Router();

// Register
router.post(
  '/register',
  [
    body('name', 'Name is required').trim().notEmpty(),
    body('email', 'Valid email is required').isEmail(),
    body('password', 'Password must be at least 6 characters').isLength({ min: 6 }),
  ],
  register
);

// Login
router.post(
  '/login',
  [
    body('email', 'Valid email is required').isEmail(),
    body('password', 'Password is required').notEmpty(),
  ],
  login
);

// Protected routes
router.get('/me', auth, getCurrentUser);
router.get('/all', auth, getAllUsers);
router.put('/update', auth, updateUser);

module.exports = router;
