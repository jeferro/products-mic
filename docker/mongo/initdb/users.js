let now = new Date()

db['users'].insertMany([
    {
        _id: "user",
        encodedPassword: "$2a$10$kldEms9W7KeY8Kf1We92E.L8L1ywr7dGGe2fuy49qraomTy4T15Ya",
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
