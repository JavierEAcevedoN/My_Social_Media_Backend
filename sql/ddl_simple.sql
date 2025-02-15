CREATE Table "user" (
    username VARCHAR(128) PRIMARY KEY NOT NULL,
    email VARCHAR(256) UNIQUE NOT NULL,
    full_name VARCHAR(512) NOT NULL,
    password TEXT NOT NULL,
	phone VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP,
    biography TEXT,
    profile_photo TEXT
);

CREATE Table "follow" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    followed TIMESTAMP NOT NULL,
    follow_from VARCHAR(128) NOT NULL,
    follow_to VARCHAR(128) NOT NULL,
    Foreign Key (follow_from) REFERENCES public.user(username) ON DELETE CASCADE,
    Foreign Key (follow_to) REFERENCES public.user(username) ON DELETE CASCADE,
    UNIQUE(follow_from,follow_to)
);

CREATE Table "notification" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    content TEXT NOT NULL,
    sended TIMESTAMP NOT NULL,
    readed BOOLEAN NOT NULL,
    username VARCHAR(128) NOT NULL,
    Foreign Key (username) REFERENCES public.user(username) ON DELETE CASCADE
);

CREATE Table "publication" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    content VARCHAR(500) NOT NULL,
    img_src TEXT,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP,
    tags VARCHAR(24)[],
    username VARCHAR(128) NOT NULL,
    Foreign Key (username) REFERENCES public.user(username) ON DELETE CASCADE
);

CREATE Table "comment" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    content VARCHAR(200) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP,
    tagged VARCHAR(128),
    id_publication BIGINT NOT NULL,
    username VARCHAR(128) NOT NULL,
    Foreign Key (tagged) REFERENCES public.user(username) ON DELETE CASCADE,
    Foreign Key (id_publication) REFERENCES public.publication(id) ON DELETE CASCADE,
    Foreign Key (username) REFERENCES public.user(username) ON DELETE CASCADE
);

CREATE Table "like" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    liked DATE NOT NULL,
    username VARCHAR(128) NOT NULL,
    id_publication BIGINT NOT NULL,
    Foreign Key (username) REFERENCES public.user(username) ON DELETE CASCADE,
    Foreign Key (id_publication) REFERENCES public.publication(id) ON DELETE CASCADE,
	UNIQUE(username,id_publication)
);