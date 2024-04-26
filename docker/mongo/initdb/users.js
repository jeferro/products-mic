let now = new Date()

db['users'].insertMany([
    {
        _id: "user",
        encodedPassword: "user",
        roles: [
            'user'
        ],
        metadata: {
            createdAt: now,
            createdBy: 'user',
            updatedAt: now,
            updatedBy: 'user'
        }
    }
])
