// DOM Elements
const audio = document.getElementById('audio');
const playBtn = document.getElementById('play');
const prevBtn = document.getElementById('prev');
const nextBtn = document.getElementById('next');
const shuffleBtn = document.getElementById('shuffle');
const repeatBtn = document.getElementById('repeat');
const seekBar = document.getElementById('seek');
const volumeBar = document.getElementById('volume');
const currentTimeEl = document.getElementById('current-time');
const durationEl = document.getElementById('duration');
const titleEl = document.getElementById('song-title');
const artistEl = document.getElementById('artist-name');
const albumArtEl = document.getElementById('album-art');
const genreEl = document.getElementById('genre');
const yearEl = document.getElementById('year');
const playlistContainer = document.querySelector('.playlist-container');
const favoritesContainer = document.querySelector('.favorites-container');
const recentContainer = document.querySelector('.recent-container');
const searchBar = document.getElementById('search-bar');
const voiceSearchBtn = document.getElementById('voice-search');
const tabBtns = document.querySelectorAll('.tab-btn');
const contentSections = document.querySelectorAll('.content-section');
const loadingSpinner = document.getElementById('loading');
const volumeDisplay = document.getElementById('volume-display');
const favoritesCount = document.getElementById('favorites-count');
const recentCount = document.getElementById('recent-count');

// Player State
let currentSong = 0;
let isPlaying = false;
let isShuffle = false;
let repeatMode = 0; // 0: off, 1: repeat all, 2: repeat one
let favorites = JSON.parse(localStorage.getItem('favorites')) || [];
let recentlyPlayed = JSON.parse(localStorage.getItem('recentlyPlayed')) || [];

// Song Database
const songs = [
    {
        id: 1,
        title: "Leo - Ordinary Person",
        artist: "Anirudh Ravichander",
        genre: "Cinematic",
        year: "2023",
        duration: "2:18",
        image: "ordinary.jpeg",
        path: "ordenary.mp3"
    },
    {
        id: 2,
        title: "Kantara - Varaha Roopam",
        artist: "Sai Vignesh",
        genre: "Electronic",
        year: "2022",
        duration: "2:03",
        image: "kantara.jpeg",
        path: "kantara_movie.mp3"
    },
    {
        id: 3,
        title: "Megham",
        artist: "Samad Khan",
        genre: "emotional",
        year: "2025",
        duration: "0:45",
        image: "Dhanush_and_Nithya.jpeg",
        path: "Dhanush_and_Nithya.mp3"
    },
    {
        id: 4,
        title: "LEO Title Card",
        artist: "Anirudh Ravichander",
        genre: "soundtrack",
        year: "2023",
        duration: "0:25",
        image: "leo.jpeg",
        path: "Leo_Title_Card.mp3"
    },
    {
        id: 5,
        title: "kerala drums",
        artist: "traditional drummers",
        genre: "Folk",
        year: "2013",
        duration: "0:58",
        image: "violin.jpeg",
        path: "violin.mp3"
    },
    {
        id: 6,
        title: "Taqdeer",
        artist: "Debarshi Raj Pal",
        genre: "Instrumental",
        year: "2019",
        duration: "0:59",
        image: "Taqdeer.jpeg",
        path: "Taqdeer.mp3"
    },
    {
        id: 7,
        title: "Krishna Flute Music",
        artist: "Jaideep Verma",
        genre: "Instrumental",
        year: "2024",
        duration: "0:30",
        image: "Krishna.jpeg",
        path: "Krishna.mp3"
    },
    {
        id: 8,
        title: "Chhoti Story",
        artist: "Anirudh Ravichander",
        genre: "Hindi Song",
        year: "2021",
        duration: "5:04",
        image: "Chhoti_Story.jpeg",
        path: "Chhoti_Story.mp3"
    },
    {
        id: 9,
        title: "Illuminati",
        artist: "Vinayak Sasikumar",
        genre: "Dance-pop",
        year: "2024",
        duration: "2:28",
        image: "Illuminati.jpeg",
        path: "Illuminati.mp3"
    },
    {
        id: 10,
        title: "Why This Kolaveri Di",
        artist: "Anirudh Ravichander",
        genre: "folk instrumental",
        year: "2012",
        duration: "4:08",
        image: "kolavari_di.jpeg",
        path: "kolavari_di.mp3"
    }
];

// Initialize Player
function initPlayer() {
    loadSong(currentSong);
    renderPlaylist();
    renderFavorites();
    renderRecent();
    updateCounts();
    setupEventListeners();
}

// Load Song
function loadSong(index) {
    if (index < 0 || index >= songs.length) return;
    
    const song = songs[index];
    
    // Show loading
    showLoading();
    
    // Update UI
    titleEl.textContent = song.title;
    artistEl.textContent = song.artist;
    genreEl.textContent = song.genre;
    yearEl.textContent = song.year;
    albumArtEl.src = song.image;
    
    // Load audio
    audio.src = song.path;
    audio.load();
    
    // Add to recently played
    addToRecentlyPlayed(song);
    
    // Update active song in playlist
    updateActiveSong();
    
    // Hide loading when ready
    audio.addEventListener('loadeddata', hideLoading, { once: true });
}

// Play/Pause
function togglePlay() {
    if (isPlaying) {
        pause();
    } else {
        play();
    }
}

function play() {
    audio.play();
    isPlaying = true;
    playBtn.innerHTML = '<i class="fas fa-pause"></i>';
    albumArtEl.style.transform = 'scale(1.02)';
}

function pause() {
    audio.pause();
    isPlaying = false;
    playBtn.innerHTML = '<i class="fas fa-play"></i>';
    albumArtEl.style.transform = 'scale(1)';
}

// Navigation
function nextSong() {
    if (isShuffle) {
        currentSong = Math.floor(Math.random() * songs.length);
    } else {
        currentSong = (currentSong + 1) % songs.length;
    }
    loadSong(currentSong);
    if (isPlaying) play();
}

function prevSong() {
    if (isShuffle) {
        currentSong = Math.floor(Math.random() * songs.length);
    } else {
        currentSong = (currentSong - 1 + songs.length) % songs.length;
    }
    loadSong(currentSong);
    if (isPlaying) play();
}

// Shuffle Toggle
function toggleShuffle() {
    isShuffle = !isShuffle;
    shuffleBtn.classList.toggle('active', isShuffle);
    showNotification(isShuffle ? 'Shuffle enabled' : 'Shuffle disabled');
}

// Repeat Toggle
function toggleRepeat() {
    repeatMode = (repeatMode + 1) % 3;
    repeatBtn.classList.toggle('active', repeatMode > 0);
    
    const modes = ['Repeat off', 'Repeat all', 'Repeat one'];
    const icons = ['fa-redo', 'fa-redo', 'fa-redo-alt'];
    
    repeatBtn.innerHTML = `<i class="fas ${icons[repeatMode]}"></i>`;
    showNotification(modes[repeatMode]);
}

// Audio Event Handlers
function handleAudioEnd() {
    if (repeatMode === 2) { // Repeat one
        audio.currentTime = 0;
        play();
    } else if (repeatMode === 1) { // Repeat all
        nextSong();
    } else if (currentSong < songs.length - 1) {
        nextSong();
    } else {
        pause();
    }
}

// Time Updates
function updateTime() {
    const current = audio.currentTime;
    const duration = audio.duration;
    
    if (duration) {
        const progress = (current / duration) * 100;
        seekBar.value = progress;
        
        // Update track progress visual
        seekBar.style.background = `linear-gradient(to right, var(--primary-color) ${progress}%, var(--bg-secondary) ${progress}%)`;
    }
    
    currentTimeEl.textContent = formatTime(current);
    durationEl.textContent = formatTime(duration);
}

function formatTime(seconds) {
    if (isNaN(seconds)) return '0:00';
    const minutes = Math.floor(seconds / 60);
    const secs = Math.floor(seconds % 60);
    return `${minutes}:${secs.toString().padStart(2, '0')}`;
}

// Volume Control
function updateVolume() {
    const volume = volumeBar.value;
    audio.volume = volume;
    volumeDisplay.textContent = `${Math.round(volume * 100)}%`;
    
    // Update volume icon
    const volumeIcon = document.querySelector('.volume-container i');
    if (volume == 0) {
        volumeIcon.className = 'fas fa-volume-mute';
    } else if (volume < 0.5) {
        volumeIcon.className = 'fas fa-volume-down';
    } else {
        volumeIcon.className = 'fas fa-volume-up';
    }
}

// Seek Control
function seek() {
    const seekTime = (seekBar.value / 100) * audio.duration;
    audio.currentTime = seekTime;
}

// Favorites Management
function toggleFavorite(songId) {
    const songIndex = favorites.findIndex(fav => fav.id === songId);
    
    if (songIndex === -1) {
        const song = songs.find(s => s.id === songId);
        favorites.push(song);
        showNotification(`Added "${song.title}" to favorites`);
    } else {
        const song = favorites[songIndex];
        favorites.splice(songIndex, 1);
        showNotification(`Removed "${song.title}" from favorites`);
    }
    
    localStorage.setItem('favorites', JSON.stringify(favorites));
    renderFavorites();
    updateCounts();
}

function isFavorite(songId) {
    return favorites.some(fav => fav.id === songId);
}

// Recently Played Management
function addToRecentlyPlayed(song) {
    // Remove if already exists
    recentlyPlayed = recentlyPlayed.filter(recent => recent.id !== song.id);
    
    // Add to beginning
    recentlyPlayed.unshift(song);
    
    // Keep only last 10
    recentlyPlayed = recentlyPlayed.slice(0, 10);
    
    localStorage.setItem('recentlyPlayed', JSON.stringify(recentlyPlayed));
    renderRecent();
    updateCounts();
}

// Search Functionality
function searchSongs(query) {
    const filteredSongs = songs.filter(song => 
        song.title.toLowerCase().includes(query.toLowerCase()) ||
        song.artist.toLowerCase().includes(query.toLowerCase()) ||
        song.genre.toLowerCase().includes(query.toLowerCase())
    );
    
    renderPlaylist(filteredSongs);
}

// Voice Search
function startVoiceSearch() {
    if (!('webkitSpeechRecognition' in window) && !('SpeechRecognition' in window)) {
        showNotification('Voice search not supported in this browser');
        return;
    }
    
    const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
    recognition.continuous = false;
    recognition.interimResults = false;
    recognition.lang = 'en-US';
    
    recognition.onstart = () => {
        voiceSearchBtn.innerHTML = '<i class="fas fa-microphone-slash"></i>';
        voiceSearchBtn.style.background = '#ef4444';
        showNotification('Listening...');
    };
    
    recognition.onresult = (event) => {
        const transcript = event.results[0][0].transcript;
        searchBar.value = transcript;
        searchSongs(transcript);
        showNotification(`Searched for: "${transcript}"`);
    };
    
    recognition.onend = () => {
        voiceSearchBtn.innerHTML = '<i class="fas fa-microphone"></i>';
        voiceSearchBtn.style.background = 'var(--primary-color)';
    };
    
    recognition.onerror = (event) => {
        showNotification('Voice search error. Please try again.');
        voiceSearchBtn.innerHTML = '<i class="fas fa-microphone"></i>';
        voiceSearchBtn.style.background = 'var(--primary-color)';
    };
    
    recognition.start();
}

// Rendering Functions
function renderPlaylist(songsToRender = songs) {
    playlistContainer.innerHTML = '';
    
    songsToRender.forEach((song, index) => {
        const songItem = createSongItem(song, index, 'playlist');
        playlistContainer.appendChild(songItem);
    });
}

function renderFavorites() {
    favoritesContainer.innerHTML = '';
    
    if (favorites.length === 0) {
        favoritesContainer.innerHTML = '<p style="text-align: center; color: var(--text-secondary); padding: 20px;">No favorite songs yet</p>';
        return;
    }
    
    favorites.forEach((song, index) => {
        const songItem = createSongItem(song, index, 'favorites');
        favoritesContainer.appendChild(songItem);
    });
}

function renderRecent() {
    recentContainer.innerHTML = '';
    
    if (recentlyPlayed.length === 0) {
        recentContainer.innerHTML = '<p style="text-align: center; color: var(--text-secondary); padding: 20px;">No recently played songs</p>';
        return;
    }
    
    recentlyPlayed.forEach((song, index) => {
        const songItem = createSongItem(song, index, 'recent');
        recentContainer.appendChild(songItem);
    });
}

function createSongItem(song, index, type) {
    const songItem = document.createElement('div');
    songItem.className = `song-item ${currentSong === songs.findIndex(s => s.id === song.id) ? 'active' : ''}`;
    
    songItem.innerHTML = `
        <img src="${song.image}" alt="${song.title}" onerror="this.src='https://via.placeholder.com/50x50/6366f1/white?text=♪'">
        <div class="song-details">
            <h4>${song.title}</h4>
            <p>${song.artist} • ${song.genre}</p>
        </div>
        <div class="song-actions">
            <button class="action-btn favorite ${isFavorite(song.id) ? 'favorite' : ''}" 
                    onclick="toggleFavorite(${song.id})" title="Add to favorites">
                <i class="fas fa-heart"></i>
            </button>
            <button class="action-btn" onclick="playSong(${song.id})" title="Play">
                <i class="fas fa-play"></i>
            </button>
        </div>
    `;
    
    songItem.addEventListener('click', (e) => {
        if (!e.target.closest('.song-actions')) {
            playSong(song.id);
        }
    });
    
    return songItem;
}

function playSong(songId) {
    const songIndex = songs.findIndex(s => s.id === songId);
    if (songIndex !== -1) {
        currentSong = songIndex;
        loadSong(currentSong);
        play();
    }
}

// Tab Management
function switchTab(tabName) {
    // Update tab buttons
    tabBtns.forEach(btn => btn.classList.remove('active'));
    document.querySelector(`[data-tab="${tabName}"]`).classList.add('active');
    
    // Update content sections
    contentSections.forEach(section => section.classList.remove('active'));
    document.getElementById(tabName).classList.add('active');
}

// Update Active Song
function updateActiveSong() {
    document.querySelectorAll('.song-item').forEach(item => {
        item.classList.remove('active');
    });
    
    const currentSongItems = document.querySelectorAll(`.song-item`);
    currentSongItems.forEach(item => {
        const songTitle = item.querySelector('h4').textContent;
        if (songTitle === songs[currentSong].title) {
            item.classList.add('active');
        }
    });
}

// Update Counts
function updateCounts() {
    favoritesCount.textContent = `${favorites.length} tracks`;
    recentCount.textContent = `${recentlyPlayed.length} tracks`;
}

// Utility Functions
function showLoading() {
    loadingSpinner.style.display = 'flex';
}

function hideLoading() {
    loadingSpinner.style.display = 'none';
}

function showNotification(message) {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: var(--primary-color);
        color: white;
        padding: 12px 20px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        z-index: 1001;
        animation: slideIn 0.3s ease;
    `;
    
    document.body.appendChild(notification);
    
    // Remove after 3 seconds
    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Keyboard Shortcuts
function handleKeyboardShortcuts(e) {
    if (e.target.tagName === 'INPUT') return;
    
    switch(e.key) {
        case ' ':
            e.preventDefault();
            togglePlay();
            break;
        case 'ArrowRight':
            nextSong();
            break;
        case 'ArrowLeft':
            prevSong();
            break;
        case 'ArrowUp':
            e.preventDefault();
            volumeBar.value = Math.min(1, parseFloat(volumeBar.value) + 0.1);
            updateVolume();
            break;
        case 'ArrowDown':
            e.preventDefault();
            volumeBar.value = Math.max(0, parseFloat(volumeBar.value) - 0.1);
            updateVolume();
            break;
        case 's':
            toggleShuffle();
            break;
        case 'r':
            toggleRepeat();
            break;
    }
}

// Event Listeners Setup
function setupEventListeners() {
    // Control buttons
    playBtn.addEventListener('click', togglePlay);
    prevBtn.addEventListener('click', prevSong);
    nextBtn.addEventListener('click', nextSong);
    shuffleBtn.addEventListener('click', toggleShuffle);
    repeatBtn.addEventListener('click', toggleRepeat);
    
    // Audio events
    audio.addEventListener('timeupdate', updateTime);
    audio.addEventListener('ended', handleAudioEnd);
    audio.addEventListener('loadstart', showLoading);
    audio.addEventListener('canplay', hideLoading);
    
    // Controls
    seekBar.addEventListener('input', seek);
    volumeBar.addEventListener('input', updateVolume);
    
    // Search
    searchBar.addEventListener('input', (e) => {
        const query = e.target.value;
        if (query.trim()) {
            searchSongs(query);
        } else {
            renderPlaylist();
        }
    });
    
    voiceSearchBtn.addEventListener('click', startVoiceSearch);
    
    // Tabs
    tabBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            switchTab(btn.dataset.tab);
        });
    });
    
    // Keyboard shortcuts
    document.addEventListener('keydown', handleKeyboardShortcuts);
    
    // Theme toggle (optional)
    const themeToggle = document.getElementById('theme-toggle');
    if (themeToggle) {
        themeToggle.addEventListener('click', toggleTheme);
    }
    
    // Initialize volume
    updateVolume();
}

// Theme Toggle (Optional Feature)
function toggleTheme() {
    document.body.classList.toggle('light-theme');
    const themeToggle = document.getElementById('theme-toggle');
    
    if (document.body.classList.contains('light-theme')) {
        themeToggle.innerHTML = '<i class="fas fa-sun"></i>';
        localStorage.setItem('theme', 'light');
    } else {
        themeToggle.innerHTML = '<i class="fas fa-moon"></i>';
        localStorage.setItem('theme', 'dark');
    }
}

// Load saved theme
function loadTheme() {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'light') {
        document.body.classList.add('light-theme');
        document.getElementById('theme-toggle').innerHTML = '<i class="fas fa-sun"></i>';
    }
}

// Visitor Counter (Optional Feature)
function updateVisitorCounter() {
    let visitors = localStorage.getItem('visitorCount') || 0;
    visitors = parseInt(visitors) + 1;
    localStorage.setItem('visitorCount', visitors);
    
    // Add counter to header if element exists
    const counterEl = document.getElementById('visitor-counter');
    if (counterEl) {
        counterEl.textContent = `Visitors: ${visitors}`;
    }
}

// Initialize Everything
document.addEventListener('DOMContentLoaded', () => {
    initPlayer();
    loadTheme();
    updateVisitorCounter();
    
    // Add CSS animations
    const style = document.createElement('style');
    style.textContent = `
        @keyframes slideIn {
            from { transform: translateX(100%); opacity: 0; }
            to { transform: translateX(0); opacity: 1; }
        }
        
        @keyframes slideOut {
            from { transform: translateX(0); opacity: 1; }
            to { transform: translateX(100%); opacity: 0; }
        }
        
        .light-theme {
            --text-primary: #1f2937;
            --text-secondary: #6b7280;
            --bg-primary: #f9fafb;
            --bg-secondary: #e5e7eb;
            --bg-card: rgba(255, 255, 255, 0.9);
            --border-color: rgba(0, 0, 0, 0.1);
            --shadow-color: rgba(0, 0, 0, 0.1);
        }
        
        .song-item {
            animation: fadeIn 0.3s ease;
        }
        
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        
        .album-art-container {
            animation: rotate 20s linear infinite;
            animation-play-state: ${isPlaying ? 'running' : 'paused'};
        }
        
        @keyframes rotate {
            from { transform: rotate(0deg); }
            to { transform: rotate(360deg); }
        }
    `;
    document.head.appendChild(style);
});

// Auto-save favorites and recent on page unload
window.addEventListener('beforeunload', () => {
    localStorage.setItem('favorites', JSON.stringify(favorites));
    localStorage.setItem('recentlyPlayed', JSON.stringify(recentlyPlayed));
});