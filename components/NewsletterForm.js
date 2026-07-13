'use client';

import { useState } from 'react';

export default function NewsletterForm() {
    const [email, setEmail] = useState('');

    const handleNewsletterSubmit = (e) => {
        e.preventDefault();
        alert(`Thank you for subscribing with ${email}! You'll receive our latest travel updates.`);
        setEmail('');
    };

    return (
        <form className="newsletter-form" onSubmit={handleNewsletterSubmit}>
            <input
                type="email"
                placeholder="Enter your email address"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                suppressHydrationWarning
            />
            <button type="submit" className="btn btn-primary">Subscribe</button>
        </form>
    );
}
