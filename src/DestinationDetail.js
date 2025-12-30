import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

function DestinationDetail() {
    const { slug } = useParams();
    const [destination, setDestination] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);

    useEffect(() => {
        fetch('/destinations.json')
            .then(res => res.json())
            .then(data => {
                // Filter
                const found = data.find(dest => dest.slug === slug);
                if (found) {
                    setDestination(found);
                    document.title = `${found.name} - Elite Adventures`;
                } else {
                    setError(true);
                }
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching destination:', err);
                setError(true);
                setLoading(false);
            });
    }, [slug]); 

    if (loading) {
        return (
            <div className="container" style={{ padding: '100px 20px', textAlign: 'center' }}>
                <h1>Loading...</h1>
            </div>
        );
    }

    //  Error state
    if (error || !destination) {
        return (
            <div className="container" style={{ padding: '100px 20px', textAlign: 'center' }}>
                <h1>Destination Not Found</h1>
                <p>The destination you're looking for doesn't exist.</p>
                <Link to="/destinations" className="btn btn-primary" style={{ marginTop: '20px' }}>
                    Browse Destinations
                </Link>
            </div>
        );
    }

    // JSX
    return (
        <div className="destination-detail-page">
            <header className="destination-detail-header">
                <img src={destination.image} alt={destination.name} />
                <div className="destination-detail-overlay">
                    <div className="container">
                        <h1>{destination.name}</h1>
                        <div className="destination-meta">
                            <span>📍 {destination.location}</span>
                            <span>⭐ {destination.rating} Rating</span>
                            <span>🗓️ {destination.duration}</span>
                            <span>🎯 {destination.type.charAt(0).toUpperCase() + destination.type.slice(1)} Trip</span>
                        </div>
                    </div>
                </div>
            </header>

            <section className="destination-detail-content">
                <div className="container">
                    <div className="detail-grid">
                        <div className="detail-main">
                            <div className="detail-section">
                                <h2>About This Trip</h2>
                                <p>{destination.description}</p>
                            </div>

                            <div className="detail-section gallery-section">
                                <h2>Photo Gallery</h2>
                                <div className="gallery-grid">
                                    {destination.gallery && destination.gallery.map((img, index) => (
                                        <img
                                            key={index}
                                            src={img}
                                            alt={`${destination.name} view ${index + 1}`}
                                            loading="lazy"
                                        />
                                    ))}
                                </div>
                            </div>
                            <div className="detail-section">
                                <h2>Trip Highlights</h2>
                                <ul className="highlights-list">
                                    {destination.highlights && destination.highlights.map((highlight, index) => (
                                        <li key={index}>✨ {highlight}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className="detail-section">
                                <h2>What's Included</h2>
                                <ul className="included-list">
                                    {destination.included && destination.included.map((item, index) => (
                                        <li key={index}>✓ {item}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className="detail-section">
                                <h2>Day-by-Day Itinerary</h2>
                                <ul className="itinerary-list">
                                    {destination.itinerary && destination.itinerary.map((day) => (
                                        <li key={day.day} className="itinerary-item">
                                            <div className="itinerary-day">
                                                <span>Day</span>
                                                {day.day}
                                            </div>
                                            <div className="itinerary-content">
                                                <h4>{day.title}</h4>
                                                <p>{day.description}</p>
                                            </div>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                        <div className="detail-sidebar">
                            <div className="booking-card">
                                <div className="booking-price">
                                    <span className="price">
                                        ₹{Number(destination.price).toLocaleString('en-IN')}
                                    </span>
                                    <span>per person</span>
                                </div>
                                <div className="booking-details">
                                    <div>
                                        <span>Duration</span>
                                        <strong>{destination.duration}</strong>
                                    </div>
                                    <div>
                                        <span>Type</span>
                                        <strong>{destination.type.charAt(0).toUpperCase() + destination.type.slice(1)}</strong>
                                    </div>
                                    <div>
                                        <span>Location</span>
                                        <strong>{destination.location}</strong>
                                    </div>
                                    <div>
                                        <span>Rating</span>
                                        <strong>⭐ {destination.rating}/5</strong>
                                    </div>
                                </div>
                                
                                <Link
                                    to={`/contact?destination=${encodeURIComponent(destination.name)}`}
                                    className="btn btn-primary btn-full"
                                >
                                    Book This Trip
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default DestinationDetail;
