import { useState } from 'react';

function AuthModal({ isOpen, onClose, onLoginSuccess }) {
    const [activeTab, setActiveTab] = useState('login');
    const [loginEmail, setLoginEmail] = useState('');
    const [loginPassword, setLoginPassword] = useState('');
    const [registerName, setRegisterName] = useState('');
    const [registerEmail, setRegisterEmail] = useState('');
    const [registerPassword, setRegisterPassword] = useState('');
    const [error, setError] = useState('');
    const [hint, setHint] = useState('');

    if (!isOpen) return null;

    const handleLogin = (e) => {
        e.preventDefault();
        setError('');
        setHint('');

        if (!loginEmail || !loginPassword) {
            setError('Please fill in all fields');
            return;
        }
        const users = JSON.parse(localStorage.getItem('eliteAdventureUsers') || '[]');
        const emailExists = users.some(u => u.email === loginEmail);

        if (!emailExists) {
            // Auto-redirect to register tab and pre-fill email
            setRegisterEmail(loginEmail);
            setActiveTab('register');
            setHint('No account found — please create one below!');
            return;
        }

        const user = users.find(u => u.email === loginEmail && u.password === loginPassword);
        if (user) {
            localStorage.setItem('currentUser', JSON.stringify(user));
            onLoginSuccess(user);
            onClose();
            resetForms();
        } else {
            setError('Incorrect password. Please try again.');
        }
    };

    const handleRegister = (e) => {
        e.preventDefault();
        setError('');
        setHint('');

        if (!registerName || !registerEmail || !registerPassword) {
            setError('Please fill in all fields');
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(registerEmail)) {
            setError('Please enter a valid email address');
            return;
        }

        const users = JSON.parse(localStorage.getItem('eliteAdventureUsers') || '[]');

        if (users.some(u => u.email === registerEmail)) {
            setError('Email already registered. Try signing in.');
            return;
        }

        const newUser = {
            id: Date.now().toString(),
            name: registerName,
            email: registerEmail,
            password: registerPassword
        };

        users.push(newUser);
        localStorage.setItem('eliteAdventureUsers', JSON.stringify(users));
        localStorage.setItem('currentUser', JSON.stringify(newUser));

        // Auto-login immediately after registration
        onLoginSuccess(newUser);
        onClose();
        resetForms();
    };

    const resetForms = () => {
        setLoginEmail('');
        setLoginPassword('');
        setRegisterName('');
        setRegisterEmail('');
        setRegisterPassword('');
        setError('');
        setHint('');
        setActiveTab('login');
    };

    const handleClose = () => {
        resetForms();
        onClose();
    };

    const switchTab = (tab) => {
        setActiveTab(tab);
        setError('');
        setHint('');
    };

    return (
        <div className="auth-modal-overlay" onClick={handleClose}>
            <div className="auth-modal" onClick={(e) => e.stopPropagation()}>
                <button className="auth-modal-close" onClick={handleClose}>✕</button>

                <div className="auth-modal-logo">
                    <img src="/images/logo.png" alt="Elite Adventures" />
                </div>
                <h2 className="auth-modal-title">
                    {activeTab === 'login' ? 'Welcome Back' : 'Create Account'}
                </h2>

                <div className="auth-tabs">
                    <button
                        className={`auth-tab ${activeTab === 'login' ? 'active' : ''}`}
                        onClick={() => switchTab('login')}
                    >
                        Sign In
                    </button>
                    <button
                        className={`auth-tab ${activeTab === 'register' ? 'active' : ''}`}
                        onClick={() => switchTab('register')}
                    >
                        Register
                    </button>
                </div>

                {hint && <div className="auth-hint">{hint}</div>}
                {error && <div className="auth-error">{error}</div>}

                {activeTab === 'login' ? (
                    <div className="auth-form-container">
                        <form onSubmit={handleLogin}>
                            <div className="auth-input-group">
                                <span className="auth-input-icon">✉️</span>
                                <input
                                    type="email"
                                    placeholder="Email address"
                                    value={loginEmail}
                                    onChange={(e) => setLoginEmail(e.target.value)}
                                />
                            </div>
                            <div className="auth-input-group">
                                <span className="auth-input-icon">🔒</span>
                                <input
                                    type="password"
                                    placeholder="Password"
                                    value={loginPassword}
                                    onChange={(e) => setLoginPassword(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="auth-submit-btn">
                                Sign In
                            </button>
                        </form>
                        <p className="auth-switch-text">
                            Don't have an account?{' '}
                            <button className="auth-switch-link" onClick={() => switchTab('register')}>
                                Create one
                            </button>
                        </p>
                    </div>
                ) : (
                    <div className="auth-form-container">
                        <form onSubmit={handleRegister}>
                            <div className="auth-input-group">
                                <span className="auth-input-icon">👤</span>
                                <input
                                    type="text"
                                    placeholder="Full Name"
                                    value={registerName}
                                    onChange={(e) => setRegisterName(e.target.value)}
                                />
                            </div>
                            <div className="auth-input-group">
                                <span className="auth-input-icon">✉️</span>
                                <input
                                    type="email"
                                    placeholder="Email address"
                                    value={registerEmail}
                                    onChange={(e) => setRegisterEmail(e.target.value)}
                                />
                            </div>
                            <div className="auth-input-group">
                                <span className="auth-input-icon">🔒</span>
                                <input
                                    type="password"
                                    placeholder="Password"
                                    value={registerPassword}
                                    onChange={(e) => setRegisterPassword(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="auth-submit-btn">
                                Create Account & Sign In
                            </button>
                        </form>
                        <p className="auth-switch-text">
                            Already have an account?{' '}
                            <button className="auth-switch-link" onClick={() => switchTab('login')}>
                                Sign in
                            </button>
                        </p>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AuthModal;
