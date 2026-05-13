import { Navigate } from 'react-router-dom';
import { useAuthStore } from '../context/store';

export const ProtectedRoute = ({ children }) => {
  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  return children;
};
