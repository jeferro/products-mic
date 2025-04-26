let now = new Date()

db['products'].insertMany([
    {
        _id: "0000001",
        typeId: "fruit",
        name: {
          "en-US": "Apple",
          "es-ES": "Manzana",
        },
        status: "PUBLISHED",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "0000002",
        typeId: "fruit",
        name: {
          "en-US": "Kiwi",
          "es-ES": "Kiwi",
        },
        status: "PUBLISHED",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    },
    {
        _id: "0000003",
        typeId: "fruit",
        name: {
          "en-US": "Banana",
          "es-ES": "Banana",
        },
        status: "UNPUBLISHED",
        metadata: {
            createdBy: 'user',
            createdAt: now,
            updatedBy: 'user',
            updatedAt: now
        }
    }
])