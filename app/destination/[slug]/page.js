import fs from 'fs';
import path from 'path';
import Link from 'next/link';

export async function generateMetadata({ params }) {
    const { slug } = await params;
    const filePath = path.join(process.cwd(), 'public', 'destinations.json');
    const fileContent = fs.readFileSync(filePath, 'utf8');
    const destinations = JSON.parse(fileContent);
    const destination = destinations.find(dest => dest.slug === slug);
    
    return {
        title: destination ? `${destination.name} - Elite Adventures` : 'Destination Not Found',
    };
}

export default async function DestinationDetailPage({ params }) {
    const { slug } = await params;
    const filePath = path.join(process.cwd(), 'public', 'destinations.json');
    const fileContent = fs.readFileSync(filePath, 'utf8');
    const destinations = JSON.parse(fileContent);
    const destination = destinations.find(dest => dest.slug === slug);

    if (!destination) {
        return (
            <div className="container" style={{ padding: '100px 20px', textAlign: 'center' }}>
                <h1>Destination Not Found</h1>
                <p>The destination you're looking for doesn't exist.</p>
                <Link href="/destinations" className="btn btn-primary" style={{ marginTop: '20px' }}>
                    Browse Destinations
                </Link>
            </div>
        );
    }

    return (
        <div className="destination-detail-page">
            <header className="destination-detail-header reveal">
                <img src={destination.image} alt={destination.name} />
                <div className="destination-detail-overlay">
                    <div className="container">
                        <h1 className="reveal delay-1">{destination.name}</h1>
                        <div className="destination-meta reveal delay-2">
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
                            <div className="detail-section reveal">
                                <h2>About This Trip</h2>
                                <p>{destination.description}</p>
                            </div>

                            <div className="detail-section gallery-section reveal">
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
                            <div className="detail-section reveal">
                                <h2>Trip Highlights</h2>
                                <ul className="highlights-list">
                                    {destination.highlights && destination.highlights.map((highlight, index) => (
                                        <li key={index}>✨ {highlight}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className="detail-section reveal">
                                <h2>What's Included</h2>
                                <ul className="included-list">
                                    {destination.included && destination.included.map((item, index) => (
                                        <li key={index}>✓ {item}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className="detail-section reveal">
                                <h2>Day-by-Day Itinerary</h2>
                                <ul className="itinerary-list">
                                    {destination.itinerary && destination.itinerary.map((day) => (
                                        <li key={day.day} className="itinerary-item reveal">
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
                        <div className="detail-sidebar reveal delay-2">
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
                                    href={`/contact?destination=${encodeURIComponent(destination.name)}`}
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
