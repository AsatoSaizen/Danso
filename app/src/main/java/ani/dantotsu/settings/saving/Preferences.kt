package ani.dantotsu.settings.saving

import android.graphics.Color
import ani.dantotsu.connections.comments.AuthResponse
import ani.dantotsu.connections.mal.MAL
import ani.dantotsu.media.SearchHistory
import ani.dantotsu.notifications.comment.CommentStore
import ani.dantotsu.notifications.subscription.SubscriptionStore
import ani.dantotsu.settings.saving.internal.Location
import ani.dantotsu.settings.saving.internal.Pref

enum class PrefName(val data: Pref) {
    //General
    SharedUserID(Pref(Location.General, Boolean::class, true)),
    OfflineView(Pref(Location.General, Int::class, 0)),
    DownloadManager(Pref(Location.General, Int::class, 0)),
    NSFWExtension(Pref(Location.General, Boolean::class, true)),
    ContinueMedia(Pref(Location.General, Boolean::class, true)),
    SearchSources(Pref(Location.General, Boolean::class, false)),
    RecentlyListOnly(Pref(Location.General, Boolean::class, false)),
    SettingsPreferDub(Pref(Location.General, Boolean::class, false)),
    SubscriptionCheckingNotifications(Pref(Location.General, Boolean::class, true)),
    CheckUpdate(Pref(Location.General, Boolean::class, true)),
    VerboseLogging(Pref(Location.General, Boolean::class, false)),
    DohProvider(Pref(Location.General, Int::class, 0)),
    HidePrivate(Pref(Location.General, Boolean::class, false)),
    DefaultUserAgent(
        Pref(
            Location.General,
            String::class,
            "Mozilla/5.0 (Linux; Android 13; Pixel 6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36"
        )
    ),
    AnimeExtensionRepos(Pref(Location.General, Set::class, setOf<String>())),
    MangaExtensionRepos(Pref(Location.General, Set::class, setOf<String>())),
    NovelExtensionRepos(Pref(Location.General, Set::class, setOf<String>())),
    AnimeSourcesOrder(Pref(Location.General, List::class, listOf<String>())),
    MangaSourcesOrder(Pref(Location.General, List::class, listOf<String>())),
    SortedAnimeSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    SortedMangaSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    SortedCharacterSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    SortedStaffSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    SortedStudioSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    SortedUserSH(Pref(Location.General, List::class, listOf<SearchHistory>())),
    NovelSourcesOrder(Pref(Location.General, List::class, listOf<String>())),
    CommentNotificationInterval(Pref(Location.General, Int::class, 0)),
    AnilistNotificationInterval(Pref(Location.General, Int::class, 3)),
    SubscriptionNotificationInterval(Pref(Location.General, Int::class, 2)),
    LastAnilistNotificationId(Pref(Location.General, Int::class, 0)),
    AnilistFilteredTypes(Pref(Location.General, Set::class, setOf<String>())),
    UseAlarmManager(Pref(Location.General, Boolean::class, false)),
    IncludeAnimeList(Pref(Location.General, Boolean::class, true)),
    IncludeMangaList(Pref(Location.General, Boolean::class, true)),
    AdultOnly(Pref(Location.General, Boolean::class, false)),
    CommentsEnabled(Pref(Location.General, Int::class, 0)),
    EnableSocks5Proxy(Pref(Location.General, Boolean::class, false)),
    ProxyAuthEnabled(Pref(Location.General, Boolean::class, false)),
    AniMangaSearchDirect(Pref(Location.General, Boolean::class, true)),

    //User Interface
    UseOLED(Pref(Location.UI, Boolean::class, false)),
    UseCustomTheme(Pref(Location.UI, Boolean::class, false)),
    CustomThemeInt(Pref(Location.UI, Int::class, Color.parseColor("#6200EE"))),
    UseSourceTheme(Pref(Location.UI, Boolean::class, false)),
    UseMaterialYou(Pref(Location.UI, Boolean::class, false)),
    Theme(Pref(Location.UI, String::class, "PURPLE")),
    SkipExtensionIcons(Pref(Location.UI, Boolean::class, false)),
    DarkMode(Pref(Location.UI, Int::class, 0)),
    ShowYtButton(Pref(Location.UI, Boolean::class, true)),
    AnimeDefaultView(Pref(Location.UI, Int::class, 0)),
    MangaDefaultView(Pref(Location.UI, Int::class, 0)),
    BlurBanners(Pref(Location.UI, Boolean::class, true)),
    BlurRadius(Pref(Location.UI, Float::class, 2f)),
    BlurSampling(Pref(Location.UI, Float::class, 2f)),
    ImmersiveMode(Pref(Location.UI, Boolean::class, false)),
    SmallView(Pref(Location.UI, Boolean::class, true)),
    DefaultStartUpTab(Pref(Location.UI, Int::class, 1)),
    HomeLayout(
        Pref(
            Location.UI,
            List::class,
            listOf(true, false, false, true, false, false, true, true)
        )
    ),
    BannerAnimations(Pref(Location.UI, Boolean::class, true)),
    LayoutAnimations(Pref(Location.UI, Boolean::class, true)),
    TrendingScroller(Pref(Location.UI, Boolean::class, true)),
    AnimationSpeed(Pref(Location.UI, Float::class, 1f)),
    ListGrid(Pref(Location.UI, Boolean::class, true)),
    PopularMangaList(Pref(Location.UI, Boolean::class, true)),
    PopularAnimeList(Pref(Location.UI, Boolean::class, true)),
    AnimeListSortOrder(Pref(Location.UI, String::class, "score")),
    MangaListSortOrder(Pref(Location.UI, String::class, "score")),
    CommentSortOrder(Pref(Location.UI, String::class, "newest")),
    FollowerLayout(Pref(Location.UI, Int::class, 0)),
    ShowNotificationRedDot(Pref(Location.UI, Boolean::class, true)),


    //Player
    DefaultSpeed(Pref(Location.Player, Int::class, 5)),
    CursedSpeeds(Pref(Location.Player, Boolean::class, false)),
    Resize(Pref(Location.Player, Int::class, 0)),
    Subtitles(Pref(Location.Player, Boolean::class, true)),
    TextviewSubtitles(Pref(Location.Player, Boolean::class, false)),
    SubLanguage(Pref(Location.Player, Int::class, 9)),
    PrimaryColor(Pref(Location.Player, Int::class, Color.WHITE)),
    SecondaryColor(Pref(Location.Player, Int::class, Color.BLACK)),
    Outline(Pref(Location.Player, Int::class, 0)),
    SubBackground(Pref(Location.Player, Int::class, Color.TRANSPARENT)),
    SubWindow(Pref(Location.Player, Int::class, Color.TRANSPARENT)),
    SubAlpha(Pref(Location.Player, Float::class, 1f)),
    SubStroke(Pref(Location.Player, Float::class, 8f)),
    SubBottomMargin(Pref(Location.Player, Float::class, 1f)),
    Font(Pref(Location.Player, Int::class, 0)),
    FontSize(Pref(Location.Player, Int::class, 20)),
    Locale(Pref(Location.Player, Int::class, 2)),
    TimeStampsEnabled(Pref(Location.Player, Boolean::class, true)),
    AutoHideTimeStamps(Pref(Location.Player, Boolean::class, true)),
    UseProxyForTimeStamps(Pref(Location.Player, Boolean::class, false)),
    ShowTimeStampButton(Pref(Location.Player, Boolean::class, true)),
    AutoSkipOPED(Pref(Location.Player, Boolean::class, false)),
    AutoSkipRecap(Pref(Location.Player, Boolean::class, false)),
    AutoPlay(Pref(Location.Player, Boolean::class, true)),
    AutoSkipFiller(Pref(Location.Player, Boolean::class, false)),
    AskIndividualPlayer(Pref(Location.Player, Boolean::class, true)),
    ChapterZeroPlayer(Pref(Location.Player, Boolean::class, true)),
    UpdateForHPlayer(Pref(Location.Player, Boolean::class, false)),
    WatchPercentage(Pref(Location.Player, Float::class, 0.8f)),
    AlwaysContinue(Pref(Location.Player, Boolean::class, true)),
    FocusPause(Pref(Location.Player, Boolean::class, true)),
    Gestures(Pref(Location.Player, Boolean::class, true)),
    DoubleTap(Pref(Location.Player, Boolean::class, true)),
    FastForward(Pref(Location.Player, Boolean::class, true)),
    SeekTime(Pref(Location.Player, Int::class, 10)),
    SkipTime(Pref(Location.Player, Int::class, 85)),
    Cast(Pref(Location.Player, Boolean::class, true)),
    UseInternalCast(Pref(Location.Player, Boolean::class, false)),
    Pip(Pref(Location.Player, Boolean::class, true)),
    RotationPlayer(Pref(Location.Player, Boolean::class, true)),
    TorrentEnabled(Pref(Location.Player, Boolean::class, false)),
    UseAdditionalCodec(Pref(Location.Player, Boolean::class, false)),

    //Reader
    ShowSource(Pref(Location.Reader, Boolean::class, true)),
    ShowSystemBars(Pref(Location.Reader, Boolean::class, false)),
    AutoDetectWebtoon(Pref(Location.Reader, Boolean::class, true)),
    AskIndividualReader(Pref(Location.Reader, Boolean::class, true)),
    ChapterZeroReader(Pref(Location.Reader, Boolean::class, true)),
    UpdateForHReader(Pref(Location.Reader, Boolean::class, false)),
    Direction(Pref(Location.Reader, Int::class, 0)),
    LayoutReader(Pref(Location.Reader, Int::class, 2)),
    DualPageModeReader(Pref(Location.Reader, Int::class, 1)),
    OverScrollMode(Pref(Location.Reader, Boolean::class, true)),
    TrueColors(Pref(Location.Reader, Boolean::class, false)),
    Rotation(Pref(Location.Reader, Boolean::class, true)),
    Padding(Pref(Location.Reader, Boolean::class, true)),
    HideScrollBar(Pref(Location.Reader, Boolean::class, false)),
    HidePageNumbers(Pref(Location.Reader, Boolean::class, false)),
    HorizontalScrollBar(Pref(Location.Reader, Boolean::class, true)),
    KeepScreenOn(Pref(Location.Reader, Boolean::class, false)),
    VolumeButtonsReader(Pref(Location.Reader, Boolean::class, false)),
    WrapImages(Pref(Location.Reader, Boolean::class, false)),
    LongClickImage(Pref(Location.Reader, Boolean::class, true)),
    CropBorders(Pref(Location.Reader, Boolean::class, false)),
    CropBorderThreshold(Pref(Location.Reader, Int::class, 10)),

    //Novel Reader
    CurrentThemeName(Pref(Location.NovelReader, String::class, "Default")),
    LayoutNovel(Pref(Location.NovelReader, Int::class, 0)),
    DualPageModeNovel(Pref(Location.NovelReader, Int::class, 1)),
    LineHeight(Pref(Location.NovelReader, Float::class, 1.4f)),
    Margin(Pref(Location.NovelReader, Float::class, 0.06f)),
    Justify(Pref(Location.NovelReader, Boolean::class, true)),
    Hyphenation(Pref(Location.NovelReader, Boolean::class, true)),
    UseDarkThemeNovel(Pref(Location.NovelReader, Boolean::class, false)),
    UseOledThemeNovel(Pref(Location.NovelReader, Boolean::class, false)),
    Invert(Pref(Location.NovelReader, Boolean::class, false)),
    MaxInlineSize(Pref(Location.NovelReader, Int::class, 720)),
    MaxBlockSize(Pref(Location.NovelReader, Int::class, 1440)),
    HorizontalScrollBarNovel(Pref(Location.NovelReader, Boolean::class, true)),
    KeepScreenOnNovel(Pref(Location.NovelReader, Boolean::class, false)),
    VolumeButtonsNovel(Pref(Location.NovelReader, Boolean::class, false)),

    //Irrelevant
    Incognito(Pref(Location.Irrelevant, Boolean::class, false)),
    OfflineMode(Pref(Location.Irrelevant, Boolean::class, false)),
    DiscordStatus(Pref(Location.Irrelevant, String::class, "online")),
    ShowAniListIcon(Pref(Location.Irrelevant, Boolean::class, false)),
    DownloadsKeys(Pref(Location.Irrelevant, String::class, "")),
    NovelLastExtCheck(Pref(Location.Irrelevant, Long::class, 0L)),
    ImageUrl(Pref(Location.Irrelevant, String::class, "")),
    AllowOpeningLinks(Pref(Location.Irrelevant, Boolean::class, false)),
    SearchStyle(Pref(Location.Irrelevant, Int::class, 0)),
    HasUpdatedPrefs(Pref(Location.Irrelevant, Boolean::class, false)),
    LangSort(Pref(Location.Irrelevant, String::class, "all")),
    GenresList(Pref(Location.Irrelevant, Set::class, setOf<String>())),
    TagsListIsAdult(Pref(Location.Irrelevant, Set::class, setOf<String>())),
    TagsListNonAdult(Pref(Location.Irrelevant, Set::class, setOf<String>())),
    MakeDefault(Pref(Location.Irrelevant, Boolean::class, true)),
    FirstComment(Pref(Location.Irrelevant, Boolean::class, true)),
    CommentAuthResponse(Pref(Location.Irrelevant, AuthResponse::class, "")),
    CommentTokenExpiry(Pref(Location.Irrelevant, Long::class, 0L)),
    LogToFile(Pref(Location.Irrelevant, Boolean::class, false)),
    RecentGlobalNotification(Pref(Location.Irrelevant, Int::class, 0)),
    CommentNotificationStore(Pref(Location.Irrelevant, List::class, listOf<CommentStore>())),
    SubscriptionNotificationStore(
        Pref(
            Location.Irrelevant,
            List::class,
            listOf<SubscriptionStore>()
        )
    ),
    UnreadCommentNotifications(Pref(Location.Irrelevant, Int::class, 0)),
    DownloadsDir(Pref(Location.Irrelevant, String::class, "")),
    OC(Pref(Location.Irrelevant, Boolean::class, false)),
    RefreshStatus(Pref(Location.Irrelevant, Boolean::class, false)),
    rpcEnabled(Pref(Location.Irrelevant, Boolean::class, true)),

    //Protected
    DiscordToken(Pref(Location.Protected, String::class, "")),
    DiscordId(Pref(Location.Protected, String::class, "")),
    DiscordUserName(Pref(Location.Protected, String::class, "")),
    DiscordAvatar(Pref(Location.Protected, String::class, "")),
    AnilistToken(Pref(Location.Protected, String::class, "")),
    AnilistUserName(Pref(Location.Protected, String::class, "")),
    AnilistUserId(Pref(Location.Protected, String::class, "")),
    MALCodeChallenge(Pref(Location.Protected, String::class, "")),
    MALToken(Pref(Location.Protected, MAL.ResponseToken::class, "")),
    AppPassword(Pref(Location.Protected, String::class, "")),
    BiometricToken(Pref(Location.Protected, String::class, "")),
    OverridePassword(Pref(Location.Protected, Boolean::class, false)),
    Socks5ProxyHost(Pref(Location.Protected, String::class, "")),
    Socks5ProxyPort(Pref(Location.Protected, String::class, "")),
    Socks5ProxyUsername(Pref(Location.Protected, String::class, "")),
    Socks5ProxyPassword(Pref(Location.Protected, String::class, "")),
}
