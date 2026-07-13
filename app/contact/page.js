import ContactForm from '../../components/ContactForm';
import { Suspense } from 'react';

export default function ContactPage() {
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
                        <h2>Send Us a Message</h2>
                        <Suspense fallback={<div>Loading form...</div>}>
                            <ContactForm />
                        </Suspense>
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
