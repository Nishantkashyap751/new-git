'use client';

import { useState } from 'react';
import { loginUser, registerUser } from '@/app/actions';

function AuthModal({ isOpen, onClose, onLoginSuccess }) {
    const [activeTab, setActiveTab] = useState('login');
    const [loginEmail, setLoginEmail] = useState('');
    const [loginPassword, setLoginPassword] = useState('');
    const [registerName, setRegisterName] = useState('');
    const [registerEmail, setRegisterEmail] = useState('');
    const [registerPassword, setRegisterPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    if (!isOpen) return null;

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        setIsLoading(true);

        if (!loginEmail || !loginPassword) {
            setError('Please fill in all fields');
            setIsLoading(false);
            return;
        }

        try {
            const result = await loginUser(loginEmail, loginPassword);
            
            if (result.success) {
                // Keep local storage as a cache for the current session if needed, 
                // but rely on server for validation
                localStorage.setItem('currentUser', JSON.stringify(result.user));
                setSuccess('Login successful!');
                setTimeout(() => {
                    onLoginSuccess(result.user);
                    onClose();
                    resetForms();
                }, 1000);
            } else {
                setError(result.error);
            }
        } catch (err) {
            setError('An error occurred during login');
        } finally {
            setIsLoading(false);
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        setIsLoading(true);

        if (!registerName || !registerEmail || !registerPassword) {
            setError('Please fill in all fields');
            setIsLoading(false);
            return;
        }

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(registerEmail)) {
            setError('Please enter a valid email address');
            setIsLoading(false);
            return;
        }

        try {
            const formData = new FormData();
            formData.append('name', registerName);
            formData.append('email', registerEmail);
            formData.append('password', registerPassword);

            const result = await registerUser(formData);

            if (result.success) {
                setSuccess('Registration successful! Please login.');
                setTimeout(() => {
                    setActiveTab('login');
                    setLoginEmail(registerEmail);
                    setRegisterName('');
                    setRegisterEmail('');
                    setRegisterPassword('');
                    setSuccess('');
                }, 1500);
            } else {
                setError(result.error);
            }
        } catch (err) {
            setError('An error occurred during registration');
        } finally {
            setIsLoading(false);
        }
    };

    const resetForms = () => {
        setLoginEmail('');
        setLoginPassword('');
        setRegisterName('');
        setRegisterEmail('');
        setRegisterPassword('');
        setError('');
        setSuccess('');
    };

    const handleClose = () => {
        resetForms();
        onClose();
    };

    return (
        <div className="auth-modal-overlay" onClick={handleClose}>
            <div className="auth-modal" onClick={(e) => e.stopPropagation()}>
                <button className="auth-modal-close" onClick={handleClose}>
                    ✕
                </button>
                <div className="auth-tabs">
                    <button
                        className={`auth-tab ${activeTab === 'login' ? 'active' : ''}`}
                        onClick={() => {
                            setActiveTab('login');
                            setError('');
                            setSuccess('');
                        }}
                    >
                        Login
                    </button>
                    <button
                        className={`auth-tab ${activeTab === 'register' ? 'active' : ''}`}
                        onClick={() => {
                            setActiveTab('register');
                            setError('');
                            setSuccess('');
                        }}
                    >
                        Register
                    </button>
                </div>

                {activeTab === 'login' ? (
                    <div className="auth-form-container">
                        <h2>Welcome Back</h2>
                        <form onSubmit={handleLogin}>
                            <input
                                type="email"
                                placeholder="Email"
                                value={loginEmail}
                                onChange={(e) => setLoginEmail(e.target.value)}
                                disabled={isLoading}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={loginPassword}
                                onChange={(e) => setLoginPassword(e.target.value)}
                                disabled={isLoading}
                            />
                            {error && <div className="auth-error">{error}</div>}
                            {success && <div className="auth-success">{success}</div>}
                            <button type="submit" className="auth-submit-btn" disabled={isLoading}>
                                {isLoading ? 'Processing...' : 'Login'}
                            </button>
                        </form>
                    </div>
                ) : (
                    <div className="auth-form-container">
                        <h2>Create Account</h2>
                        <form onSubmit={handleRegister}>
                            <input
                                type="text"
                                placeholder="Full Name"
                                value={registerName}
                                onChange={(e) => setRegisterName(e.target.value)}
                                disabled={isLoading}
                            />
                            <input
                                type="email"
                                placeholder="Email"
                                value={registerEmail}
                                onChange={(e) => setRegisterEmail(e.target.value)}
                                disabled={isLoading}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={registerPassword}
                                onChange={(e) => setRegisterPassword(e.target.value)}
                                disabled={isLoading}
                            />
                            {error && <div className="auth-error">{error}</div>}
                            {success && <div className="auth-success">{success}</div>}
                            <button type="submit" className="auth-submit-btn" disabled={isLoading}>
                                {isLoading ? 'Processing...' : 'Register'}
                            </button>
                        </form>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AuthModal;
