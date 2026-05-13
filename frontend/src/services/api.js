import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:5000/api';

const api = axios.create({
  baseURL: API_URL,
});

// Add token to requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// User services
export const userService = {
  register: (data) => api.post('/users/register', data),
  login: (data) => api.post('/users/login', data),
  getCurrentUser: () => api.get('/users/me'),
  getAllUsers: () => api.get('/users/all'),
  updateUser: (data) => api.put('/users/update', data),
};

// Project services
export const projectService = {
  createProject: (data) => api.post('/projects', data),
  getAllProjects: () => api.get('/projects'),
  getProjectById: (id) => api.get(`/projects/${id}`),
  updateProject: (id, data) => api.put(`/projects/${id}`, data),
  addMember: (id, data) => api.post(`/projects/${id}/members`, data),
  removeMember: (id, data) => api.delete(`/projects/${id}/members`, { data }),
  deleteProject: (id) => api.delete(`/projects/${id}`),
};

// Task services
export const taskService = {
  createTask: (data) => api.post('/tasks', data),
  getTasksByProject: (projectId, params) =>
    api.get(`/tasks/project/${projectId}`, { params }),
  getTaskById: (id) => api.get(`/tasks/${id}`),
  updateTask: (id, data) => api.put(`/tasks/${id}`, data),
  addComment: (id, data) => api.post(`/tasks/${id}/comments`, data),
  deleteTask: (id) => api.delete(`/tasks/${id}`),
  getDashboardStats: () => api.get('/tasks/dashboard/stats'),
};

export default api;
