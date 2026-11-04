import 'package:flutter/material.dart';

class MovieDetailsPage extends StatelessWidget {
  final int movieId;
  final String movieTitle;

  const MovieDetailsPage({
    super.key,
    required this.movieId,
    required this.movieTitle,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Movie Details')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(movieTitle, style: Theme.of(context).textTheme.headlineMedium),
            const SizedBox(height: 16),
            Text('TMDB ID: $movieId'),
            const SizedBox(height: 24),
            ElevatedButton(
              onPressed: () {},
              child: const Text('Add to Watchlist'),
            ),
          ],
        ),
      ),
    );
  }
}
