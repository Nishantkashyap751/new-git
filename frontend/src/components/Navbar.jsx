import { Link, useNavigate } from 'react-router-dom';
import { useAuthStore } from '../context/store';
import './Navbar.css';

export const Navbar = () => {
  const { user, logout } = useAuthStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  if (!user) return null;

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/dashboard" className="navbar-brand">
          📋 Task Manager
        </Link>

        <div className="nav-menu">
          <Link to="/dashboard" className="nav-link">Dashboard</Link>
          <Link to="/projects" className="nav-link">Projects</Link>
        </div>

        <div className="nav-user">
          <span className="user-name">{user.name}</span>
          <span className="user-role">{user.role}</span>
          <button className="btn-logout" onClick={handleLogout}>Logout</button>
        </div>
      </div>
    </nav>
  );
};
