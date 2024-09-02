let now = new Date()

db['product_reviews'].insertMany([
    {
        _id: "user::apple",
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
        _id: "admin::apple",
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
        _id: "user::kiwi",
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