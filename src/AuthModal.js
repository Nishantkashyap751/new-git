import { useState } from 'react';

function AuthModal({ isOpen, onClose, onLoginSuccess }) {
    const [activeTab, setActiveTab] = useState('login');
    const [loginEmail, setLoginEmail] = useState('');
    const [loginPassword, setLoginPassword] = useState('');
    const [registerName, setRegisterName] = useState('');
    const [registerEmail, setRegisterEmail] = useState('');
    const [registerPassword, setRegisterPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    if (!isOpen) return null;

    const handleLogin = (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');

        if (!loginEmail || !loginPassword) {
            setError('Please fill in all fields');
            return;
        }
        const users = JSON.parse(localStorage.getItem('eliteAdventureUsers') || '[]');

        const user = users.find(
            u => u.email === loginEmail && u.password === loginPassword
        );
        if (user) {
            localStorage.setItem('currentUser', JSON.stringify(user));
            setSuccess('Login successful!');
            setTimeout(() => {
                onLoginSuccess(user);
                onClose();
                resetForms();
            }, 1000);
        } else {
            setError('Invalid email or password');
        }
    };
    const handleRegister = (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');

        if (!registerName || !registerEmail || !registerPassword) {
            setError('Please fill in all fields');
            return;
        }

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(registerEmail)) {
            setError('Please enter a valid email address');
            return;
        }
        // Get existing users
        const users = JSON.parse(localStorage.getItem('eliteAdventureUsers') || '[]');

        if (users.some(u => u.email === registerEmail)) {
            setError('Email already registered');
            return;
        }

        // Create new user
        const newUser = {
            id: Date.now().toString(),
            name: registerName,
            email: registerEmail,
            password: registerPassword
        };

        // Save to localStorage
        users.push(newUser);
        localStorage.setItem('eliteAdventureUsers', JSON.stringify(users));
        localStorage.setItem('currentUser', JSON.stringify(newUser));

        setSuccess('Registration successful!');
        setTimeout(() => {
            onLoginSuccess(newUser);
            onClose();
            resetForms();
        }, 1000);
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
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={loginPassword}
                                onChange={(e) => setLoginPassword(e.target.value)}
                            />
                            {error && <div className="auth-error">{error}</div>}
                            {success && <div className="auth-success">{success}</div>}
                            <button type="submit" className="auth-submit-btn">
                                Login
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
                            />
                            <input
                                type="email"
                                placeholder="Email"
                                value={registerEmail}
                                onChange={(e) => setRegisterEmail(e.target.value)}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={registerPassword}
                                onChange={(e) => setRegisterPassword(e.target.value)}
                            />
                            {error && <div className="auth-error">{error}</div>}
                            {success && <div className="auth-success">{success}</div>}
                            <button type="submit" className="auth-submit-btn">
                                Register
                            </button>
                        </form>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AuthModal;
