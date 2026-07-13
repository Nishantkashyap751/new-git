'use client';

import Link from 'next/link';
import { useEffect, useState, useRef } from 'react';
import AuthModal from './AuthModal';

export default function Navbar() {
    const [menuOpen, setMenuOpen] = useState(false);
    const [user, setUser] = useState(null);
    const [authModalOpen, setAuthModalOpen] = useState(false);
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [scrolled, setScrolled] = useState(false);
    const dropdownRef = useRef(null);

    useEffect(() => {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) setUser(currentUser);

        const handleScroll = () => {
            if (window.scrollY > 50) {
                setScrolled(true);
            } else {
                setScrolled(false);
            }
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    useEffect(() => {
        function handleClickOutside(e) {
            if (dropdownRef.current && !dropdownRef.current.contains(e.target)) {
                setDropdownOpen(false);
            }
        }
        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('currentUser');
        setUser(null);
        setDropdownOpen(false);
    };

    const handleLoginSuccess = (userData) => {
        setUser(userData);
    };

    const firstName = user?.name?.split(' ')[0] || '';
    const initial = firstName.charAt(0).toUpperCase();

    const [theme, setTheme] = useState('dark');

    useEffect(() => {
        const savedTheme = localStorage.getItem('theme') || 'dark';
        setTheme(savedTheme);
        document.documentElement.setAttribute('data-theme', savedTheme);
    }, []);

    const toggleTheme = () => {
        const newTheme = theme === 'light' ? 'dark' : 'light';
        setTheme(newTheme);
        localStorage.setItem('theme', newTheme);
        document.documentElement.setAttribute('data-theme', newTheme);
    };

    return (
        <>
            <nav className={`navbar ${scrolled ? 'scrolled' : ''}`}>
                <div className="nav-container">
                    <Link href="/" className="logo">
                        <img src="/images/logo.png" alt="Elite Adventures" />
                    </Link>
                    <div className="nav-right" style={{ display: 'flex', alignItems: 'center' }}>
                        <button
                            className={`mobile-menu-btn ${menuOpen ? 'active' : ''}`}
                            onClick={() => setMenuOpen(!menuOpen)}
                            aria-label="Toggle menu"
                        >
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>
                        <ul className={`nav-links ${menuOpen ? 'active' : ''}`}>
                            <li><Link href="/" className="nav-link" onClick={() => setMenuOpen(false)}>Home</Link></li>
                            <li><Link href="/destinations" className="nav-link" onClick={() => setMenuOpen(false)}>Destinations</Link></li>
                            <li><Link href="/contact" className="nav-link" onClick={() => setMenuOpen(false)}>Contact</Link></li>

                            <li className="auth-item">
                                {user ? (
                                    <div className="user-menu" ref={dropdownRef}>
                                        <button
                                            className="user-menu-btn"
                                            onClick={() => setDropdownOpen(!dropdownOpen)}
                                        >
                                            <span className="user-avatar">{initial}</span>
                                            <span className="user-name">{firstName}</span>
                                            <svg className={`chevron ${dropdownOpen ? 'open' : ''}`} width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" style={{ transition: 'transform 0.3s', transform: dropdownOpen ? 'rotate(180deg)' : 'rotate(0)' }}>
                                                <polyline points="6 9 12 15 18 9" />
                                            </svg>
                                        </button>
                                        {dropdownOpen && (
                                            <div className="user-dropdown">
                                                <button onClick={handleLogout} className="dropdown-item logout">
                                                    Sign Out
                                                </button>
                                            </div>
                                        )}
                                    </div>
                                ) : (
                                    <button
                                        onClick={() => { setAuthModalOpen(true); setMenuOpen(false); }}
                                        className="signin-btn"
                                    >
                                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
                                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                                            <circle cx="12" cy="7" r="4" />
                                        </svg>
                                        Sign In
                                    </button>
                                )}
                            </li>
                            <li className="theme-item">
                                <button
                                    className="theme-toggle"
                                    onClick={toggleTheme}
                                    aria-label="Toggle Theme"
                                >
                                    {theme === 'light' ? (
                                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                                            <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"></path>
                                        </svg>
                                    ) : (
                                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                                            <circle cx="12" cy="12" r="5"></circle>
                                            <line x1="12" y1="1" x2="12" y2="3"></line>
                                            <line x1="12" y1="21" x2="12" y2="23"></line>
                                            <line x1="4.22" y1="4.22" x2="5.64" y2="5.64"></line>
                                            <line x1="18.36" y1="18.36" x2="19.78" y2="19.78"></line>
                                            <line x1="1" y1="12" x2="3" y2="12"></line>
                                            <line x1="21" y1="12" x2="23" y2="12"></line>
                                            <line x1="4.22" y1="19.78" x2="5.64" y2="18.36"></line>
                                            <line x1="18.36" y1="5.64" x2="19.78" y2="4.22"></line>
                                        </svg>
                                    )}
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <AuthModal
                isOpen={authModalOpen}
                onClose={() => setAuthModalOpen(false)}
                onLoginSuccess={handleLoginSuccess}
            />
        </>
    );
}
