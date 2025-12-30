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

                            {user ? (
                                <li>
                                    <button onClick={handleLogout} className="logout-btn">
                                        Hi, {user.name.split(' ')[0]} (Logout)
                                    </button>
                                </li>
                            ) : (
                                <li>
                                    <button
                                        onClick={() => {
                                            setAuthModalOpen(true);
                                            setMenuOpen(false);
                                        }}
                                        className="login-btn"
                                    >
                                        Login / Register
                                    </button>
                                </li>
                            )}
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
                                <img src="/images/logo1.png" alt="Elite Adventures" className="footer-logo" />
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
