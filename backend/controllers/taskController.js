const Task = require('../models/Task');
const Project = require('../models/Project');
const { validationResult } = require('express-validator');

// Create Task
const createTask = async (req, res) => {
  try {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }

    const { title, description, projectId, assigneeId, priority, dueDate } = req.body;

    // Check if project exists
    const project = await Project.findById(projectId);
    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    // Check if user is member of project
    const isMember =
      project.owner.toString() === req.user.id ||
      project.members.some((m) => m.user.toString() === req.user.id);

    if (!isMember) {
      return res.status(403).json({ message: 'Not authorized to create task in this project' });
    }

    const task = new Task({
      title,
      description,
      project: projectId,
      assignee: assigneeId || null,
      createdBy: req.user.id,
      priority,
      dueDate,
    });

    await task.save();
    await task.populate('assignee', 'name email');
    await task.populate('createdBy', 'name email');

    res.status(201).json({
      message: 'Task created successfully',
      task,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Get Tasks by Project
const getTasksByProject = async (req, res) => {
  try {
    const { projectId } = req.params;
    const { status, priority, assignee } = req.query;

    const filter = { project: projectId };
    if (status) filter.status = status;
    if (priority) filter.priority = priority;
    if (assignee) filter.assignee = assignee;

    const tasks = await Task.find(filter)
      .populate('assignee', 'name email')
      .populate('createdBy', 'name email')
      .sort({ createdAt: -1 });

    res.status(200).json({ tasks });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Get Task by ID
const getTaskById = async (req, res) => {
  try {
    const task = await Task.findById(req.params.id)
      .populate('assignee', 'name email')
      .populate('createdBy', 'name email')
      .populate('comments.user', 'name email avatar');

    if (!task) {
      return res.status(404).json({ message: 'Task not found' });
    }

    res.status(200).json({ task });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Update Task
const updateTask = async (req, res) => {
  try {
    const { title, description, status, priority, assigneeId, dueDate } = req.body;

    const task = await Task.findById(req.params.id);
    if (!task) {
      return res.status(404).json({ message: 'Task not found' });
    }

    // Check if user is creator or assignee
    if (task.createdBy.toString() !== req.user.id && task.assignee?.toString() !== req.user.id) {
      // Check if user is project admin
      const project = await Project.findById(task.project);
      const isAdmin = project.owner.toString() === req.user.id;
      if (!isAdmin) {
        return res.status(403).json({ message: 'Not authorized to update this task' });
      }
    }

    Object.assign(task, {
      title,
      description,
      status,
      priority,
      assignee: assigneeId,
      dueDate,
    });

    await task.save();
    await task.populate('assignee', 'name email');
    await task.populate('createdBy', 'name email');

    res.status(200).json({
      message: 'Task updated successfully',
      task,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Add Comment to Task
const addComment = async (req, res) => {
  try {
    const { text } = req.body;

    const task = await Task.findById(req.params.id);
    if (!task) {
      return res.status(404).json({ message: 'Task not found' });
    }

    task.comments.push({
      user: req.user.id,
      text,
    });

    await task.save();
    await task.populate('comments.user', 'name email avatar');

    res.status(200).json({
      message: 'Comment added successfully',
      task,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Delete Task
const deleteTask = async (req, res) => {
  try {
    const task = await Task.findById(req.params.id);
    if (!task) {
      return res.status(404).json({ message: 'Task not found' });
    }

    // Check authorization
    if (task.createdBy.toString() !== req.user.id) {
      const project = await Project.findById(task.project);
      if (project.owner.toString() !== req.user.id) {
        return res.status(403).json({ message: 'Not authorized to delete this task' });
      }
    }

    await Task.findByIdAndDelete(req.params.id);

    res.status(200).json({ message: 'Task deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Get Dashboard Stats
const getDashboardStats = async (req, res) => {
  try {
    const userProjects = await Project.find({
      $or: [{ owner: req.user.id }, { 'members.user': req.user.id }],
    });

    const projectIds = userProjects.map((p) => p._id);

    const totalTasks = await Task.countDocuments({
      $or: [{ project: { $in: projectIds } }, { assignee: req.user.id }],
    });

    const completedTasks = await Task.countDocuments({
      status: 'completed',
      $or: [{ project: { $in: projectIds } }, { assignee: req.user.id }],
    });

    const overdueTasks = await Task.countDocuments({
      isOverdue: true,
      status: { $ne: 'completed' },
      $or: [{ project: { $in: projectIds } }, { assignee: req.user.id }],
    });

    const assignedToMe = await Task.countDocuments({
      assignee: req.user.id,
      status: { $ne: 'completed' },
    });

    const myTasks = await Task.find({
      assignee: req.user.id,
      status: { $ne: 'completed' },
    })
      .populate('project', 'name')
      .sort({ dueDate: 1 })
      .limit(5);

    res.status(200).json({
      stats: {
        totalTasks,
        completedTasks,
        overdueTasks,
        assignedToMe,
      },
      myTasks,
      totalProjects: userProjects.length,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

module.exports = {
  createTask,
  getTasksByProject,
  getTaskById,
  updateTask,
  addComment,
  deleteTask,
  getDashboardStats,
};
