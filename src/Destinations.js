import { useEffect, useState } from 'react';
import DestinationCard from './DestinationCard';

function Destinations() {
    const [destinations, setDestinations] = useState([]);
    const [filteredDests, setFilteredDests] = useState([]);
    const [search, setSearch] = useState('');
    const [location, setLocation] = useState('');
    const [type, setType] = useState('');
    const [price, setPrice] = useState('');
    const [sort, setSort] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch('/destinations.json')
            .then(res => res.json())
            .then(data => {
                setDestinations(data);
                setFilteredDests(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching destinations:', error);
                setLoading(false);
            });
    }, []);

    useEffect(() => {
        let filtered = [...destinations];

        if (search) {
            filtered = filtered.filter(d =>
                d.name.toLowerCase().includes(search.toLowerCase()) ||
                d.location.toLowerCase().includes(search.toLowerCase()) ||
                d.description.toLowerCase().includes(search.toLowerCase())
            );
        }

        if (location) {
            filtered = filtered.filter(d => d.location === location);
        }

        if (type) {
            filtered = filtered.filter(d => d.type === type);
        }

        if (price) {
            const [min, max] = price.split('-').map(Number);
            filtered = filtered.filter(d => d.price >= min && d.price <= max);
        }

        if (sort === 'price-low') {
            filtered.sort((a, b) => a.price - b.price);
        } else if (sort === 'price-high') {
            filtered.sort((a, b) => b.price - a.price);
        } else if (sort === 'rating') {
            filtered.sort((a, b) => b.rating - a.rating);
        } else if (sort === 'name') {
            filtered.sort((a, b) => a.name.localeCompare(b.name));
        }

        setFilteredDests(filtered);
    }, [search, location, type, price, sort, destinations]);

    const uniqueLocations = [...new Set(destinations.map(d => d.location))];
    const uniqueTypes = [...new Set(destinations.map(d => d.type))];

    return (
        <div className="destinations-page">
            <div className="page-header">
                <h1>Explore Destinations</h1>
                <p>Find your perfect adventure from our curated collection of amazing destinations</p>
            </div>

            <div className="container">
                <div className="filters">
                    <div className="filter-group">
                        <label htmlFor="search">Search</label>
                        <input
                            type="text"
                            id="search"
                            placeholder="Search destinations..."
                            value={search}
                            onChange={(e) => setSearch(e.target.value)}
                        />
                    </div>

                    <div className="filter-group">
                        <label htmlFor="location">Location</label>
                        <select
                            id="location"
                            value={location}
                            onChange={(e) => setLocation(e.target.value)}
                        >
                            <option value="">All Locations</option>
                            {uniqueLocations.map(loc => (
                                <option key={loc} value={loc}>{loc}</option>
                            ))}
                        </select>
                    </div>

                    <div className="filter-group">
                        <label htmlFor="type">Type</label>
                        <select
                            id="type"
                            value={type}
                            onChange={(e) => setType(e.target.value)}
                        >
                            <option value="">All Types</option>
                            {uniqueTypes.map(t => (
                                <option key={t} value={t}>{t.charAt(0).toUpperCase() + t.slice(1)}</option>
                            ))}
                        </select>
                    </div>

                    <div className="filter-group">
                        <label htmlFor="price">Price Range</label>
                        <select
                            id="price"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        >
                            <option value="">All Prices</option>
                            <option value="0-50000">Under ₹50,000</option>
                            <option value="50000-100000">₹50,000 - ₹1,00,000</option>
                            <option value="100000-150000">₹1,00,000 - ₹1,50,000</option>
                            <option value="150000-999999">Above ₹1,50,000</option>
                        </select>
                    </div>

                    <div className="filter-group">
                        <label htmlFor="sort">Sort By</label>
                        <select
                            id="sort"
                            value={sort}
                            onChange={(e) => setSort(e.target.value)}
                        >
                            <option value="">Default</option>
                            <option value="price-low">Price: Low to High</option>
                            <option value="price-high">Price: High to Low</option>
                            <option value="rating">Highest Rated</option>
                            <option value="name">A-Z</option>
                        </select>
                    </div>
                </div>

                <p className="results-info">
                    {filteredDests.length} destination{filteredDests.length !== 1 ? 's' : ''} found
                </p>

                <div className="destinations-grid">
                    {loading ? (
                        <p className="text-center">Loading destinations...</p>
                    ) : filteredDests.length === 0 ? (
                        <p className="text-center" style={{ gridColumn: '1 / -1' }}>
                            No destinations found matching your criteria.
                        </p>
                    ) : (
                        filteredDests.map(dest => (
                            <DestinationCard key={dest.id} destination={dest} />
                        ))
                    )}
                </div>
            </div>
        </div>
    );
}

export default Destinations;
