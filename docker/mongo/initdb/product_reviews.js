let now = new Date()

db['product_reviews'].insertMany([
    {
        _id: "user::18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        comment: "Apple is so good",
        username: "user",
        productCode: "18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "admin::18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        comment: "I prefer bananas",
        username: "admin",
        productCode: "18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "user::bd0d848e-d8a4-4c80-bd4f-d064be1d7ce0",
        comment: "Good kiwis",
        username: "user",
        productCode: "bd0d848e-d8a4-4c80-bd4f-d064be1d7ce0",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    }
])