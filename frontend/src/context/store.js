import { create } from 'zustand';

export const useAuthStore = create((set) => ({
  user: null,
  token: localStorage.getItem('token') || null,
  isAuthenticated: !!localStorage.getItem('token'),
  loading: false,
  error: null,

  setUser: (user) => set({ user }),
  setToken: (token) => {
    if (token) {
      localStorage.setItem('token', token);
    } else {
      localStorage.removeItem('token');
    }
    set({ token, isAuthenticated: !!token });
  },
  setLoading: (loading) => set({ loading }),
  setError: (error) => set({ error }),
  logout: () => {
    localStorage.removeItem('token');
    set({ user: null, token: null, isAuthenticated: false });
  },
}));

export const useProjectStore = create((set) => ({
  projects: [],
  currentProject: null,
  loading: false,
  error: null,

  setProjects: (projects) => set({ projects }),
  setCurrentProject: (project) => set({ currentProject: project }),
  setLoading: (loading) => set({ loading }),
  setError: (error) => set({ error }),
}));

export const useTaskStore = create((set) => ({
  tasks: [],
  currentTask: null,
  loading: false,
  error: null,
  dashboardStats: null,

  setTasks: (tasks) => set({ tasks }),
  setCurrentTask: (task) => set({ currentTask: task }),
  setLoading: (loading) => set({ loading }),
  setError: (error) => set({ error }),
  setDashboardStats: (stats) => set({ dashboardStats: stats }),
}));
