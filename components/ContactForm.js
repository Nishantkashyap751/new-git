'use client';

import { useState } from 'react';
import { useSearchParams } from 'next/navigation';
import { submitInquiry } from '../app/actions';

export default function ContactForm() {
    const searchParams = useSearchParams();
    const destinationParam = searchParams.get('destination');

    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        subject: destinationParam ? 'booking' : '',
        message: destinationParam
            ? `I'm interested in booking a trip to ${destinationParam}. Please provide more information about availability and booking details.`
            : ''
    });

    const [errors, setErrors] = useState({});
    const [submitted, setSubmitted] = useState(false);
    const [submitting, setSubmitting] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));

        if (errors[name]) {
            setErrors(prev => ({
                ...prev,
                [name]: ''
            }));
        }
    };

    const validate = () => {
        const newErrors = {};

        if (!formData.name.trim()) newErrors.name = 'Please enter your name';
        if (!formData.email.trim()) {
            newErrors.email = 'Please enter your email';
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
            newErrors.email = 'Please enter a valid email address';
        }
        if (!formData.subject) newErrors.subject = 'Please select a subject';
        if (!formData.message.trim()) {
            newErrors.message = 'Please enter your message';
        } else if (formData.message.trim().length < 10) {
            newErrors.message = 'Message must be at least 10 characters';
        }

        return newErrors;
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        const newErrors = validate();
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }

        setSubmitting(true);

        // Convert state to FormData for Server Action
        const data = new FormData();
        Object.entries(formData).forEach(([key, value]) => data.append(key, value));

        try {
            const result = await submitInquiry(data);
            if (result.success) {
                setSubmitted(true);
                setFormData({ name: '', email: '', phone: '', subject: '', message: '' });
            } else {
                throw new Error(result.error);
            }
        } catch (error) {
            console.error('Error submitting form:', error);
            alert('There was an error submitting your message. Please try again.');
        } finally {
            setSubmitting(false);
        }
    };

    if (submitted) {
        return (
            <div className="form-success">
                <div className="success-icon">✓</div>
                <h3>Thank You!</h3>
                <p>Your message has been sent successfully. We'll get back to you within 24 hours.</p>
                <button
                    onClick={() => setSubmitted(false)}
                    className="btn btn-primary"
                    style={{ marginTop: '20px' }}
                >
                    Send Another Message
                </button>
            </div>
        );
    }

    return (
        <form className="contact-form" onSubmit={handleFormSubmit}>
            <div className={`form-group ${errors.name ? 'error' : ''}`}>
                <label htmlFor="name">Name *</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    placeholder="Your full name"
                />
                {errors.name && <span className="error-message">{errors.name}</span>}
            </div>

            <div className={`form-group ${errors.email ? 'error' : ''}`}>
                <label htmlFor="email">Email *</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="your.email@example.com"
                />
                {errors.email && <span className="error-message">{errors.email}</span>}
            </div>

            <div className={`form-group ${errors.phone ? 'error' : ''}`}>
                <label htmlFor="phone">Phone</label>
                <input
                    type="tel"
                    id="phone"
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                    placeholder="+91 1234567890"
                />
                {errors.phone && <span className="error-message">{errors.phone}</span>}
            </div>

            <div className={`form-group ${errors.subject ? 'error' : ''}`}>
                <label htmlFor="subject">Subject *</label>
                <select id="subject" name="subject" value={formData.subject} onChange={handleChange}>
                    <option value="">Select a subject</option>
                    <option value="booking">Booking Inquiry</option>
                    <option value="general">General Question</option>
                    <option value="support">Customer Support</option>
                    <option value="feedback">Feedback</option>
                </select>
                {errors.subject && <span className="error-message">{errors.subject}</span>}
            </div>

            <div className={`form-group ${errors.message ? 'error' : ''}`}>
                <label htmlFor="message">Message *</label>
                <textarea
                    id="message"
                    name="message"
                    value={formData.message}
                    onChange={handleChange}
                    placeholder="Tell us how we can help..."
                    rows="5"
                ></textarea>
                {errors.message && <span className="error-message">{errors.message}</span>}
            </div>

            <button type="submit" className="btn btn-primary btn-full" disabled={submitting}>
                {submitting ? 'Sending...' : 'Send Message'}
            </button>
        </form>
    );
}
