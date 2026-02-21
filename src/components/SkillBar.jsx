export default function SkillBar({ name, percentage, animate }) {
    return (
        <div className="skill-item">
            <div className="skill-header">
                <span className="skill-name">{name}</span>
                <span className="skill-percentage">{percentage}%</span>
            </div>
            <div className="skill-bar">
                <div
                    className={`skill-progress${animate ? ' animated' : ''}`}
                    data-progress={percentage}
                    style={
                        animate
                            ? { width: `${percentage}%`, '--progress-width': `${percentage}%` }
                            : {}
                    }
                />
            </div>
        </div>
    )
}
