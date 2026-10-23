import 'package:flutter/material.dart';
import 'package:frontend/service/movie_api_service.dart';

class MoviesPage extends StatelessWidget {
  const MoviesPage({super.key});
  static final MovieApiService movieApiService = MovieApiService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CineVault')),
      body: FutureBuilder<List<dynamic>>(
        future: movieApiService.getUserMovies(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          if (snapshot.hasData) {
            final movies = snapshot.data!;

            return ListView.builder(
              itemCount: movies.length,
              itemBuilder: (context, index) {
                final movie = movies[index];

                return ListTile(
                  title: Text('movie: ${movie['movieTitle']}'),
                  subtitle: Text('Status: ${movie['status']}'),
                );
              },
            );
          }
          return const Center(child: Text('No movies found'));
        },
      ),
    );
  }
}
