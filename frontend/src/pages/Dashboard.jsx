import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useProjectStore, useTaskStore } from '../context/store';
import { projectService, taskService } from '../services/api';
import './Dashboard.css';

export const Dashboard = () => {
  const { dashboardStats, setDashboardStats } = useTaskStore();
  const { projects, setProjects } = useProjectStore();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [statsRes, projectsRes] = await Promise.all([
          taskService.getDashboardStats(),
          projectService.getAllProjects(),
        ]);
        setDashboardStats(statsRes.data);
        setProjects(projectsRes.data.projects);
      } catch (err) {
        console.error('Error fetching dashboard data:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [setDashboardStats, setProjects]);

  if (loading) return <div className="loading">Loading...</div>;

  return (
    <div className="dashboard">
      <h1>Dashboard</h1>

      {dashboardStats && (
        <div className="stats-container">
          <div className="stat-card">
            <h3>{dashboardStats.stats.totalTasks}</h3>
            <p>Total Tasks</p>
          </div>
          <div className="stat-card">
            <h3>{dashboardStats.stats.completedTasks}</h3>
            <p>Completed</p>
          </div>
          <div className="stat-card alert">
            <h3>{dashboardStats.stats.overdueTasks}</h3>
            <p>Overdue</p>
          </div>
          <div className="stat-card">
            <h3>{dashboardStats.stats.assignedToMe}</h3>
            <p>Assigned to Me</p>
          </div>
        </div>
      )}

      <div className="dashboard-sections">
        <div className="section">
          <div className="section-header">
            <h2>My Tasks</h2>
            <Link to="/projects" className="btn-secondary">View All</Link>
          </div>
          {dashboardStats && dashboardStats.myTasks.length > 0 ? (
            <div className="task-list">
              {dashboardStats.myTasks.map((task) => (
                <div key={task._id} className="task-item">
                  <div className="task-info">
                    <h4>{task.title}</h4>
                    <p>{task.project.name}</p>
                  </div>
                  <div className={`priority ${task.priority}`}>{task.priority}</div>
                </div>
              ))}
            </div>
          ) : (
            <p>No tasks assigned to you</p>
          )}
        </div>

        <div className="section">
          <div className="section-header">
            <h2>Recent Projects</h2>
            <Link to="/projects" className="btn-secondary">All Projects</Link>
          </div>
          {projects && projects.length > 0 ? (
            <div className="project-list">
              {projects.slice(0, 5).map((project) => (
                <Link key={project._id} to={`/projects/${project._id}`} className="project-item">
                  <div className="project-info">
                    <h4>{project.name}</h4>
                    <p>{project.members.length} members</p>
                  </div>
                  <span className={`status ${project.status}`}>{project.status}</span>
                </Link>
              ))}
            </div>
          ) : (
            <p>No projects yet. <Link to="/projects/new">Create one</Link></p>
          )}
        </div>
      </div>
    </div>
  );
};
