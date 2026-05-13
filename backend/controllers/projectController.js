const Project = require('../models/Project');
const Task = require('../models/Task');
const { validationResult } = require('express-validator');

// Create Project
const createProject = async (req, res) => {
  try {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }

    const { name, description, dueDate } = req.body;

    const project = new Project({
      name,
      description,
      dueDate,
      owner: req.user.id,
      members: [
        {
          user: req.user.id,
          role: 'admin',
        },
      ],
    });

    await project.save();
    await project.populate('owner', 'name email');
    await project.populate('members.user', 'name email role');

    res.status(201).json({
      message: 'Project created successfully',
      project,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Get All Projects
const getAllProjects = async (req, res) => {
  try {
    const projects = await Project.find({
      $or: [{ owner: req.user.id }, { 'members.user': req.user.id }],
    })
      .populate('owner', 'name email')
      .populate('members.user', 'name email');

    res.status(200).json({ projects });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Get Project by ID
const getProjectById = async (req, res) => {
  try {
    const project = await Project.findById(req.params.id)
      .populate('owner', 'name email')
      .populate('members.user', 'name email');

    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    res.status(200).json({ project });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Update Project
const updateProject = async (req, res) => {
  try {
    const { name, description, status, dueDate } = req.body;

    const project = await Project.findById(req.params.id);
    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    // Check authorization
    if (project.owner.toString() !== req.user.id) {
      return res.status(403).json({ message: 'Not authorized to update this project' });
    }

    Object.assign(project, { name, description, status, dueDate });
    await project.save();
    await project.populate('owner', 'name email');
    await project.populate('members.user', 'name email');

    res.status(200).json({
      message: 'Project updated successfully',
      project,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Add Member to Project
const addMemberToProject = async (req, res) => {
  try {
    const { userId, role } = req.body;

    const project = await Project.findById(req.params.id);
    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    // Check authorization
    if (project.owner.toString() !== req.user.id) {
      return res.status(403).json({ message: 'Not authorized to manage project members' });
    }

    // Check if member already exists
    const memberExists = project.members.some((m) => m.user.toString() === userId);
    if (memberExists) {
      return res.status(400).json({ message: 'Member already added to project' });
    }

    project.members.push({
      user: userId,
      role: role || 'member',
    });

    await project.save();
    await project.populate('owner', 'name email');
    await project.populate('members.user', 'name email');

    res.status(200).json({
      message: 'Member added to project successfully',
      project,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Remove Member from Project
const removeMemberFromProject = async (req, res) => {
  try {
    const { userId } = req.body;

    const project = await Project.findById(req.params.id);
    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    // Check authorization
    if (project.owner.toString() !== req.user.id) {
      return res.status(403).json({ message: 'Not authorized to manage project members' });
    }

    project.members = project.members.filter((m) => m.user.toString() !== userId);
    await project.save();
    await project.populate('owner', 'name email');
    await project.populate('members.user', 'name email');

    res.status(200).json({
      message: 'Member removed from project successfully',
      project,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

// Delete Project
const deleteProject = async (req, res) => {
  try {
    const project = await Project.findById(req.params.id);
    if (!project) {
      return res.status(404).json({ message: 'Project not found' });
    }

    // Check authorization
    if (project.owner.toString() !== req.user.id) {
      return res.status(403).json({ message: 'Not authorized to delete this project' });
    }

    // Delete all tasks in the project
    await Task.deleteMany({ project: req.params.id });

    await Project.findByIdAndDelete(req.params.id);

    res.status(200).json({ message: 'Project deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

module.exports = {
  createProject,
  getAllProjects,
  getProjectById,
  updateProject,
  addMemberToProject,
  removeMemberFromProject,
  deleteProject,
};
