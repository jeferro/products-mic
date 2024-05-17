let now = new Date()

db['product_reviews'].insertMany([
    {
        _id: "user::18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        comment: "Apple is so good",
        username: "user",
        productId: "18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    }
])