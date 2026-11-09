import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend/service/movie_api_service.dart';
import 'package:http/http.dart' as http;

class MovieDetailsPage extends StatelessWidget {
  final int movieId;
  final String movieTitle;
  static final MovieApiService movieApiService = MovieApiService();

  const MovieDetailsPage({
    super.key,
    required this.movieId,
    required this.movieTitle,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Movie Details')),
      body: FutureBuilder<http.Response>(
        future: movieApiService.getMovieById(movieId),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          if (!snapshot.hasData) {
            return const Center(child: Text('No movie data available'));
          }
          if (snapshot.data!.statusCode != 200) {
            return Center(
              child: Text('Failed to load movie: ${snapshot.data!.statusCode}'),
            );
          }
          final movie = jsonDecode(snapshot.data!.body);
          return Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                movie['title'],
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              const SizedBox(height: 16),
              Text(movie['overview'] ?? 'No description available'),
              const SizedBox(height: 16),
              Text('Release Date: ${movie['release_date'] ?? 'Unknown'}'),
              const SizedBox(height: 16),
              Text(
                'Runtime: ${movie['runtime'] != null ? '${movie['runtime']} minutes' : 'Unknown'}',
              ),
              const SizedBox(height: 16),
              Text('TMDB ID: $movieId'),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: () {},
                child: const Text('Add to Watchlist'),
              ),
            ],
          );
        },
      ),
    );
  }
}
