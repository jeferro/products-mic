let now = new Date()

db['products'].insertMany([
    {
        _id: "18ff00eb-eb6b-46d3-bcc4-dc3fafc927c1",
        name: "Apple",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "bd0d848e-d8a4-4c80-bd4f-d064be1d7ce0",
        name: "Kiwi",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "f43914a7-4f44-4c93-bf0f-9cfae1f322c9",
        name: "Banana",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    }
])