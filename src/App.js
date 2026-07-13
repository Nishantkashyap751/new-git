import { useEffect, useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import AuthModal from './AuthModal';
import Contact from './Contact';
import DestinationDetail from './DestinationDetail';
import Destinations from './Destinations';
import Home from './Home';

function App() {
    const [menuOpen, setMenuOpen] = useState(false);
    const [user, setUser] = useState(null);
    const [authModalOpen, setAuthModalOpen] = useState(false);

    useEffect(() => {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) setUser(currentUser);
    }, []);

    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    const handleLogout = () => {
        localStorage.removeItem('currentUser');
        setUser(null);
    };

    const handleLoginSuccess = (userData) => {
        setUser(userData);
    };

    return (
        <Router>
            <div className="App">
                <nav className="navbar">
                    <div className="nav-container">
                        <Link to="/" className="logo">
                            <img src="/images/logo.png" alt="Elite Adventures" />
                        </Link>
                        <button
                            className={`mobile-menu-btn ${menuOpen ? 'active' : ''}`}
                            onClick={toggleMenu}
                            aria-label="Toggle menu"
                        >
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>
                        <ul className={`nav-links ${menuOpen ? 'active' : ''}`}>
                            <li><Link to="/" onClick={() => setMenuOpen(false)}>Home</Link></li>
                            <li><Link to="/destinations" onClick={() => setMenuOpen(false)}>Destinations</Link></li>
                            <li><Link to="/contact" onClick={() => setMenuOpen(false)}>Contact</Link></li>

                            <li>
                                {user ? (
                                    <div className="auth-user-group">
                                        <div className="auth-user-btn">
                                            <span className="auth-user-avatar">
                                                {user.name.charAt(0).toUpperCase()}
                                            </span>
                                            <span className="auth-user-name">
                                                {user.name.split(' ')[0]}
                                            </span>
                                        </div>
                                        <div className="auth-arrow-wrap">
                                            <button className="auth-arrow-btn" aria-label="Account options">
                                                <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
                                                    <path d="M2 4L6 8L10 4" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                                                </svg>
                                            </button>
                                            <div className="auth-dropdown">
                                                <button className="auth-signout-btn" onClick={handleLogout}>
                                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                                                        <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
                                                        <polyline points="16 17 21 12 16 7" />
                                                        <line x1="21" y1="12" x2="9" y2="12" />
                                                    </svg>
                                                    Sign Out
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                ) : (
                                    <button
                                        onClick={() => {
                                            setAuthModalOpen(true);
                                            setMenuOpen(false);
                                        }}
                                        className="auth-signin-btn"
                                    >
                                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                                            <circle cx="12" cy="7" r="4" />
                                        </svg>
                                        Sign In
                                    </button>
                                )}
                            </li>
                        </ul>
                    </div>
                </nav>

                <AuthModal
                    isOpen={authModalOpen}
                    onClose={() => setAuthModalOpen(false)}
                    onLoginSuccess={handleLoginSuccess}
                />

                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/destinations" element={<Destinations />} />
                    <Route path="/destination/:slug" element={<DestinationDetail />} />
                    <Route path="/contact" element={<Contact />} />
                </Routes>

                <footer className="footer">
                    <div className="container">
                        <div className="footer-grid">
                            <div className="footer-section footer-logo-section">
                                <img src="/images/logo.png" alt="Elite Adventures" className="footer-logo" />
                                <p>Creating unforgettable travel experiences since 2010. Your adventure starts here.</p>
                            </div>
                            <div className="footer-section">
                                <h4>Quick Links</h4>
                                <ul>
                                    <li><Link to="/">Home</Link></li>
                                    <li><Link to="/destinations">Destinations</Link></li>
                                    <li><Link to="/contact">Contact</Link></li>
                                </ul>
                            </div>
                            <div className="footer-section">
                                <h4>Contact Info</h4>
                                <ul>
                                    <li>Chitkara university</li>
                                    <li>+91 1234567890</li>
                                    <li>EliteAdventures@gmail.com</li>
                                </ul>
                            </div>
                            <div className="footer-section">
                                <h4>Follow Us</h4>
                                <div className="social-links">
                                    <a href="https://facebook.com" target="_blank" rel="noreferrer" aria-label="Facebook">📘</a>
                                    <a href="https://instagram.com" target="_blank" rel="noreferrer" aria-label="Instagram">📷</a>
                                    <a href="https://twitter.com" target="_blank" rel="noreferrer" aria-label="Twitter">🐦</a>
                                    <a href="https://youtube.com" target="_blank" rel="noreferrer" aria-label="YouTube">📺</a>
                                </div>
                            </div>
                        </div>
                        <div className="footer-bottom">
                            <p>&copy; 2024 Elite Adventures. All rights reserved.</p>
                        </div>
                    </div>
                </footer>
            </div>
        </Router>
    );
}

export default App;
