import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { taskService } from '../services/api';
import './Tasks.css';

export const TaskDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [task, setTask] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editing, setEditing] = useState(false);
  const [comment, setComment] = useState('');
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    status: '',
    priority: '',
    dueDate: '',
  });

  useEffect(() => {
    fetchTask();
  }, [id]);

  const fetchTask = async () => {
    try {
      const response = await taskService.getTaskById(id);
      setTask(response.data.task);
      setFormData({
        title: response.data.task.title,
        description: response.data.task.description,
        status: response.data.task.status,
        priority: response.data.task.priority,
        dueDate: response.data.task.dueDate?.split('T')[0] || '',
      });
    } catch (err) {
      console.error('Error fetching task:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleUpdateTask = async (e) => {
    e.preventDefault();
    try {
      await taskService.updateTask(id, formData);
      setEditing(false);
      fetchTask();
    } catch (err) {
      alert(err.response?.data?.message || 'Error updating task');
    }
  };

  const handleAddComment = async (e) => {
    e.preventDefault();
    try {
      await taskService.addComment(id, { text: comment });
      setComment('');
      fetchTask();
    } catch (err) {
      alert(err.response?.data?.message || 'Error adding comment');
    }
  };

  const handleDeleteTask = async () => {
    if (window.confirm('Are you sure?')) {
      try {
        await taskService.deleteTask(id);
        navigate('/projects');
      } catch (err) {
        alert(err.response?.data?.message || 'Error deleting task');
      }
    }
  };

  if (loading) return <div className="loading">Loading task...</div>;
  if (!task) return <div className="error">Task not found</div>;

  return (
    <div className="task-detail">
      <div className="task-header">
        <h1>{task.title}</h1>
        <div className="task-actions">
          <button className="btn-edit" onClick={() => setEditing(!editing)}>
            {editing ? 'Cancel' : 'Edit'}
          </button>
          <button className="btn-danger" onClick={handleDeleteTask}>Delete</button>
        </div>
      </div>

      {editing ? (
        <form onSubmit={handleUpdateTask} className="edit-form">
          <div className="form-group">
            <label>Title</label>
            <input
              type="text"
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Description</label>
            <textarea
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
            ></textarea>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label>Status</label>
              <select
                value={formData.status}
                onChange={(e) => setFormData({ ...formData, status: e.target.value })}
              >
                <option value="todo">To Do</option>
                <option value="in-progress">In Progress</option>
                <option value="in-review">In Review</option>
                <option value="completed">Completed</option>
              </select>
            </div>
            <div className="form-group">
              <label>Priority</label>
              <select
                value={formData.priority}
                onChange={(e) => setFormData({ ...formData, priority: e.target.value })}
              >
                <option value="low">Low</option>
                <option value="medium">Medium</option>
                <option value="high">High</option>
                <option value="urgent">Urgent</option>
              </select>
            </div>
            <div className="form-group">
              <label>Due Date</label>
              <input
                type="date"
                value={formData.dueDate}
                onChange={(e) => setFormData({ ...formData, dueDate: e.target.value })}
              />
            </div>
          </div>
          <button type="submit" className="btn-primary">Save Changes</button>
        </form>
      ) : (
        <div className="task-info">
          <p>{task.description}</p>
          <div className="task-metadata">
            <div className="meta-item">
              <label>Status:</label>
              <span className={`status ${task.status}`}>{task.status}</span>
            </div>
            <div className="meta-item">
              <label>Priority:</label>
              <span className={`priority ${task.priority}`}>{task.priority}</span>
            </div>
            <div className="meta-item">
              <label>Assigned To:</label>
              <span>{task.assignee?.name || 'Unassigned'}</span>
            </div>
            <div className="meta-item">
              <label>Created By:</label>
              <span>{task.createdBy?.name}</span>
            </div>
            {task.dueDate && (
              <div className="meta-item">
                <label>Due Date:</label>
                <span>{new Date(task.dueDate).toLocaleDateString()}</span>
              </div>
            )}
            {task.isOverdue && (
              <div className="meta-item overdue">
                <label>Status:</label>
                <span>Overdue</span>
              </div>
            )}
          </div>
        </div>
      )}

      <div className="comments-section">
        <h2>Comments</h2>
        <form onSubmit={handleAddComment} className="comment-form">
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            placeholder="Add a comment..."
            required
          ></textarea>
          <button type="submit" className="btn-primary">Comment</button>
        </form>

        <div className="comments-list">
          {task.comments && task.comments.length > 0 ? (
            task.comments.map((c, idx) => (
              <div key={idx} className="comment">
                <div className="comment-header">
                  <strong>{c.user?.name}</strong>
                  <span className="comment-date">
                    {new Date(c.createdAt).toLocaleDateString()}
                  </span>
                </div>
                <p>{c.text}</p>
              </div>
            ))
          ) : (
            <p>No comments yet</p>
          )}
        </div>
      </div>
    </div>
  );
};
