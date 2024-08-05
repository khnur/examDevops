import React, { useState } from 'react';

const Report = () => {
    const [reportType, setReportType] = useState('');
    const [reportData, setReportData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchReport = async (type) => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch(`http://localhost:8081/reports/${type}`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setReportData(data);
            setReportType(type);
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };

    const renderData = (data) => {
        if (Array.isArray(data)) {
            return data.map((item, index) => (
                <div key={index} className="report-section">
                    <pre>{JSON.stringify(item, null, 2)}</pre>
                </div>
            ));
        } else if (typeof data === 'object' && data !== null) {
            return Object.entries(data).map(([key, value], index) => (
                <div key={index} className="report-section">
                    <strong>{key}:</strong>
                    {typeof value === 'object' && value !== null ? (
                        <div className="nested-report">{renderData(value)}</div>
                    ) : (
                        ` ${value}`
                    )}
                </div>
            ));
        } else {
            return <p>{data}</p>;
        }
    };

    return (
        <div>
            <h1>Reports</h1>
            <button onClick={() => fetchReport('nationality')}>Generate Nationality Report</button>
            <button onClick={() => fetchReport('marital-status')}>Generate Marital Status Report</button>
            <button onClick={() => fetchReport('car-ownership')}>Generate Car Ownership Report</button>
            <button onClick={() => fetchReport('military-status')}>Generate Military Status Report</button>
            <button onClick={() => fetchReport('svc-status')}>Generate SVC Status Report</button>
            <button onClick={() => fetchReport('education-list-size')}>Generate Education List Size Report</button>
            <button onClick={() => fetchReport('children-count')}>Generate Children Count Report</button>
            <button onClick={() => fetchReport('combined')}>Generate Combined Report</button>

            {loading && <p>Loading...</p>}
            {error && <p>Error: {error}</p>}
            <div id="report">
                {reportData && (
                    <div>
                        <h2>{reportType.replace('-', ' ').toUpperCase()} Report:</h2>
                        {renderData(reportData)}
                    </div>
                )}
            </div>
        </div>
    );
};

export default Report;
