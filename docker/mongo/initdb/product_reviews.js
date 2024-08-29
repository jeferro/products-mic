let now = new Date()

db['product_reviews'].insertMany([
    {
        _id: "user::apple",
        comment: "Apple is so good",
        username: "user",
        productCode: "apple",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "admin::apple",
        comment: "I prefer bananas",
        username: "admin",
        productCode: "apple",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "user::kiwi",
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