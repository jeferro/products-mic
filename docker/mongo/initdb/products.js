let now = new Date()

db['products'].insertMany([
    {
        _id: "apple",
        name: {
          en_US: "Apple",
          es_ES: "Manzana",
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
        _id: "kiwi",
        name: {
          en_US: "Kiwi",
          es_ES: "Kiwi",
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
        _id: "banana",
        name: {
          en_US: "Banana",
          es_ES: "Banana",
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