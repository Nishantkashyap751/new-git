import { useState } from 'react';
import { useSearchParams } from 'react-router-dom';

function Contact() {
    const [searchParams] = useSearchParams();
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

        if (!formData.name.trim()) {
            newErrors.name = 'Please enter your name';
        }

        if (!formData.email.trim()) {
            newErrors.email = 'Please enter your email';
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
            newErrors.email = 'Please enter a valid email address';
        }

        if (formData.phone && !/^[\d\s\-+()]{10,}$/.test(formData.phone)) {
            newErrors.phone = 'Please enter a valid phone number';
        }

        if (!formData.subject) {
            newErrors.subject = 'Please select a subject';
        }

        if (!formData.message.trim()) {
            newErrors.message = 'Please enter your message';
        } else if (formData.message.trim().length < 10) {
            newErrors.message = 'Message must be at least 10 characters';
        }

        return newErrors;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newErrors = validate();
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }

        setSubmitting(true);

        try {
            const response = await fetch('/api/inquiries', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    ...formData,
                    createdAt: new Date().toISOString()
                })
            });

            if (!response.ok) throw new Error('Failed to submit form');

            setSubmitted(true);
            setFormData({ name: '', email: '', phone: '', subject: '', message: '' });

        } catch (error) {
            console.error('Error submitting form:', error);
            alert('There was an error submitting your message. Please try again.');
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="contact-page">
            <div className="page-header">
                <h1>Contact Us</h1>
                <p>Get in touch with us for any inquiries about your next adventure</p>
            </div>

            <div className="container">
                <div className="contact-grid">
                    <div className="contact-info">
                        <h2>Get In Touch</h2>
                        <p>Have questions about your dream vacation? We're here to help! Reach out to us and our travel experts will assist you in planning the perfect adventure.</p>

                        <div className="info-cards">
                            <div className="info-card">
                                <div className="info-icon">📍</div>
                                <div>
                                    <h4>Address</h4>
                                    <p>Chitkara university<br />Punjab, India</p>
                                </div>
                            </div>

                            <div className="info-card">
                                <div className="info-icon">📞</div>
                                <div>
                                    <h4>Phone</h4>
                                    <p>+91 1234567890<br />Mon-Fri: 9AM - 6PM</p>
                                </div>
                            </div>

                            <div className="info-card">
                                <div className="info-icon">✉️</div>
                                <div>
                                    <h4>Email</h4>
                                    <p>EliteAdventures@gmail.com<br />We'll respond within 24 hours</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="contact-form-container">
                        {!submitted ? (
                            <>
                                <h2>Send Us a Message</h2>

                                <form className="contact-form" onSubmit={handleSubmit}>
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
                                        {errors.name && <span className="error-message" style={{ display: 'block' }}>{errors.name}</span>}
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
                                        {errors.email && <span className="error-message" style={{ display: 'block' }}>{errors.email}</span>}
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
                                        {errors.phone && <span className="error-message" style={{ display: 'block' }}>{errors.phone}</span>}
                                    </div>

                                    <div className={`form-group ${errors.subject ? 'error' : ''}`}>
                                        <label htmlFor="subject">Subject *</label>
                                        <select
                                            id="subject"
                                            name="subject"
                                            value={formData.subject}
                                            onChange={handleChange}
                                        >
                                            <option value="">Select a subject</option>
                                            <option value="booking">Booking Inquiry</option>
                                            <option value="general">General Question</option>
                                            <option value="support">Customer Support</option>
                                            <option value="feedback">Feedback</option>
                                        </select>
                                        {errors.subject && <span className="error-message" style={{ display: 'block' }}>{errors.subject}</span>}
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
                                        {errors.message && <span className="error-message" style={{ display: 'block' }}>{errors.message}</span>}
                                    </div>

                                    <button
                                        type="submit"
                                        className="btn btn-primary btn-full"
                                        disabled={submitting}
                                    >
                                        {submitting ? 'Sending...' : 'Send Message'}
                                    </button>
                                </form>
                            </>
                        ) : (
                            // Success Message
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
                        )}
                    </div>
                </div>


                <div className="faq-section">
                    <div className="section-header">
                        <h2>Frequently Asked Questions</h2>
                        <p>Quick answers to common questions</p>
                    </div>

                    <div className="faq-grid">
                        <div className="faq-item">
                            <h4>How do I book a trip?</h4>
                            <p>Browse our destinations, select your preferred package, and click "Book This Trip". Fill out the contact form and our team will reach out to confirm your booking.</p>
                        </div>

                        <div className="faq-item">
                            <h4>What payment methods do you accept?</h4>
                            <p>We accept all major credit cards, debit cards, bank transfers, and digital payment methods. Flexible payment plans are available.</p>
                        </div>

                        <div className="faq-item">
                            <h4>Can I customize my itinerary?</h4>
                            <p>Absolutely! All our packages can be customized to suit your preferences, budget, and schedule. Contact us to discuss your requirements.</p>
                        </div>

                        <div className="faq-item">
                            <h4>What is your cancellation policy?</h4>
                            <p>Cancellations made 30+ days before departure receive a full refund. 15-29 days: 50% refund. Less than 15 days: no refund. Terms vary by package.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Contact;
