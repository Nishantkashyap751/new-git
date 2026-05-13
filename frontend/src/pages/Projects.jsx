import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { projectService, taskService, userService } from '../services/api';
import './Projects.css';

export const Projects = () => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const [formData, setFormData] = useState({ name: '', description: '', dueDate: '' });

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      const response = await projectService.getAllProjects();
      setProjects(response.data.projects);
    } catch (err) {
      console.error('Error fetching projects:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCreateProject = async (e) => {
    e.preventDefault();
    try {
      await projectService.createProject(formData);
      setFormData({ name: '', description: '', dueDate: '' });
      setShowCreateForm(false);
      fetchProjects();
    } catch (err) {
      alert(err.response?.data?.message || 'Error creating project');
    }
  };

  if (loading) return <div className="loading">Loading projects...</div>;

  return (
    <div className="projects-page">
      <div className="projects-header">
        <h1>Projects</h1>
        <button className="btn-primary" onClick={() => setShowCreateForm(!showCreateForm)}>
          {showCreateForm ? 'Cancel' : '+ New Project'}
        </button>
      </div>

      {showCreateForm && (
        <div className="create-project-form">
          <form onSubmit={handleCreateProject}>
            <div className="form-group">
              <label>Project Name</label>
              <input
                type="text"
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Description</label>
              <textarea
                value={formData.description}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              ></textarea>
            </div>
            <div className="form-group">
              <label>Due Date</label>
              <input
                type="date"
                value={formData.dueDate}
                onChange={(e) => setFormData({ ...formData, dueDate: e.target.value })}
              />
            </div>
            <button type="submit" className="btn-primary">Create Project</button>
          </form>
        </div>
      )}

      <div className="projects-grid">
        {projects.length > 0 ? (
          projects.map((project) => (
            <Link key={project._id} to={`/projects/${project._id}`} className="project-card">
              <h3>{project.name}</h3>
              <p className="description">{project.description}</p>
              <div className="project-meta">
                <span className={`status ${project.status}`}>{project.status}</span>
                <span className="members">{project.members.length} members</span>
              </div>
            </Link>
          ))
        ) : (
          <div className="empty-state">
            <p>No projects yet. Create your first project!</p>
          </div>
        )}
      </div>
    </div>
  );
};

export const ProjectDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [project, setProject] = useState(null);
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showTaskForm, setShowTaskForm] = useState(false);
  const [taskForm, setTaskForm] = useState({
    title: '',
    description: '',
    priority: 'medium',
    dueDate: '',
    assigneeId: '',
  });
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [projectRes, tasksRes, usersRes] = await Promise.all([
          projectService.getProjectById(id),
          taskService.getTasksByProject(id),
          userService.getAllUsers(),
        ]);
        setProject(projectRes.data.project);
        setTasks(tasksRes.data.tasks);
        setUsers(usersRes.data.users);
      } catch (err) {
        console.error('Error fetching project:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [id]);

  const handleCreateTask = async (e) => {
    e.preventDefault();
    try {
      await taskService.createTask({
        ...taskForm,
        projectId: id,
      });
      setTaskForm({
        title: '',
        description: '',
        priority: 'medium',
        dueDate: '',
        assigneeId: '',
      });
      setShowTaskForm(false);
      // Refresh tasks
      const tasksRes = await taskService.getTasksByProject(id);
      setTasks(tasksRes.data.tasks);
    } catch (err) {
      alert(err.response?.data?.message || 'Error creating task');
    }
  };

  const handleDeleteProject = async () => {
    if (window.confirm('Are you sure you want to delete this project?')) {
      try {
        await projectService.deleteProject(id);
        navigate('/projects');
      } catch (err) {
        alert(err.response?.data?.message || 'Error deleting project');
      }
    }
  };

  if (loading) return <div className="loading">Loading project...</div>;
  if (!project) return <div className="error">Project not found</div>;

  return (
    <div className="project-detail">
      <div className="project-header">
        <div>
          <h1>{project.name}</h1>
          <p className="description">{project.description}</p>
        </div>
        <div className="project-actions">
          <button className="btn-danger" onClick={handleDeleteProject}>Delete Project</button>
        </div>
      </div>

      <div className="project-meta">
        <span className={`status ${project.status}`}>{project.status}</span>
        <span>{project.members.length} members</span>
        {project.dueDate && <span>Due: {new Date(project.dueDate).toLocaleDateString()}</span>}
      </div>

      <div className="tasks-section">
        <div className="section-header">
          <h2>Tasks</h2>
          <button className="btn-primary" onClick={() => setShowTaskForm(!showTaskForm)}>
            {showTaskForm ? 'Cancel' : '+ New Task'}
          </button>
        </div>

        {showTaskForm && (
          <form onSubmit={handleCreateTask} className="task-form">
            <div className="form-group">
              <label>Task Title</label>
              <input
                type="text"
                value={taskForm.title}
                onChange={(e) => setTaskForm({ ...taskForm, title: e.target.value })}
                required
              />
            </div>
            <div className="form-group">
              <label>Description</label>
              <textarea
                value={taskForm.description}
                onChange={(e) => setTaskForm({ ...taskForm, description: e.target.value })}
              ></textarea>
            </div>
            <div className="form-row">
              <div className="form-group">
                <label>Priority</label>
                <select
                  value={taskForm.priority}
                  onChange={(e) => setTaskForm({ ...taskForm, priority: e.target.value })}
                >
                  <option value="low">Low</option>
                  <option value="medium">Medium</option>
                  <option value="high">High</option>
                  <option value="urgent">Urgent</option>
                </select>
              </div>
              <div className="form-group">
                <label>Assign To</label>
                <select
                  value={taskForm.assigneeId}
                  onChange={(e) => setTaskForm({ ...taskForm, assigneeId: e.target.value })}
                >
                  <option value="">Unassigned</option>
                  {users.map((user) => (
                    <option key={user._id} value={user._id}>
                      {user.name}
                    </option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label>Due Date</label>
                <input
                  type="date"
                  value={taskForm.dueDate}
                  onChange={(e) => setTaskForm({ ...taskForm, dueDate: e.target.value })}
                />
              </div>
            </div>
            <button type="submit" className="btn-primary">Create Task</button>
          </form>
        )}

        {tasks.length > 0 ? (
          <div className="tasks-list">
            {tasks.map((task) => (
              <Link key={task._id} to={`/tasks/${task._id}`} className="task-card">
                <div className="task-title">{task.title}</div>
                <div className="task-footer">
                  <span className={`status ${task.status}`}>{task.status}</span>
                  <span className={`priority ${task.priority}`}>{task.priority}</span>
                  {task.assignee && <span className="assignee">{task.assignee.name}</span>}
                </div>
              </Link>
            ))}
          </div>
        ) : (
          <p>No tasks in this project</p>
        )}
      </div>
    </div>
  );
};
