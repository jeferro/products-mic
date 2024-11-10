let now = new Date()

db['product_reviews'].insertMany([
    {
        _id: "user::1",
        username: "user",
        productCode: "apple",
        locale: "en-US",
        comment: "Apple is so good",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "admin::0000001",
        username: "admin",
        productCode: "apple",
        locale: "en-US",
        comment: "I prefer bananas",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "user::0000002",
        username: "user",
        productCode: "kiwi",
        locale: "en-US",
        comment: "Good kiwis",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    }
])